package com.censoredsoftware.demigods.battle;

import com.censoredsoftware.demigods.Demigods;
import com.censoredsoftware.demigods.data.DataManager;
import com.censoredsoftware.demigods.deity.Alliance;
import com.censoredsoftware.demigods.deity.Deity;
import com.censoredsoftware.demigods.exception.SpigotNotFoundException;
import com.censoredsoftware.demigods.language.Symbol;
import com.censoredsoftware.demigods.location.DLocation;
import com.censoredsoftware.demigods.player.DCharacter;
import com.censoredsoftware.demigods.player.DPlayer;
import com.censoredsoftware.demigods.player.Pet;
import com.censoredsoftware.demigods.player.Skill;
import com.censoredsoftware.demigods.structure.Structure;
import com.censoredsoftware.demigods.util.Configs;
import com.censoredsoftware.demigods.util.Messages;
import com.censoredsoftware.demigods.util.Randoms;
import com.censoredsoftware.demigods.util.Vehicles;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.*;

public class Battle implements ConfigurationSerializable
{
	private UUID id;
	private UUID startLoc;
	private boolean active;
	private long startTime;
	private long deleteTime;
	private Set<String> involvedPlayers;
	private Set<String> involvedTameable;
	private int killCounter;
	private int runnableId;
	private Map<String, Object> kills;
	private Map<String, Object> deaths;
	private UUID startedBy;

	public Battle()
	{
		this.kills = Maps.newHashMap();
		this.deaths = Maps.newHashMap();
		this.involvedPlayers = Sets.newHashSet();
		this.involvedTameable = Sets.newHashSet();
		this.killCounter = 0;
	}

	public Battle(UUID id, ConfigurationSection conf)
	{
		this.id = id;
		startLoc = UUID.fromString(conf.getString("startLoc"));
		active = conf.getBoolean("active");
		startTime = conf.getLong("startTime");
		deleteTime = conf.getLong("deleteTime");
		involvedPlayers = Sets.newHashSet(conf.getStringList("involvedPlayers"));
		involvedTameable = Sets.newHashSet(conf.getStringList("involvedTameable"));
		killCounter = conf.getInt("killCounter");
		runnableId = conf.getInt("runnableId");
		kills = conf.getConfigurationSection("kills").getValues(false);
		deaths = conf.getConfigurationSection("deaths").getValues(false);
		startedBy = UUID.fromString(conf.getString("startedBy"));
	}

	@Override
	public Map<String, Object> serialize()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startLoc", startLoc.toString());
		map.put("active", active);
		map.put("startTime", startTime);
		map.put("deleteTime", deleteTime);
		map.put("involvedPlayers", Lists.newArrayList(involvedPlayers));
		map.put("involvedTameable", Lists.newArrayList(involvedTameable));
		map.put("killCounter", killCounter);
		map.put("runnableId", runnableId);
		map.put("kills", kills);
		map.put("deaths", deaths);
		map.put("startedBy", startedBy.toString());
		return map;
	}

	public void generateId()
	{
		id = UUID.randomUUID();
	}

	public void setActive()
	{
		this.active = true;
		Battle.Util.save(this);
	}

	public void setInactive()
	{
		this.active = false;
		Battle.Util.save(this);
	}

	void setStartLocation(Location location)
	{
		this.startLoc = DLocation.Util.create(location).getId();
	}

	void setStartTime(long time)
	{
		this.startTime = time;
	}

	void setDeleteTime(long time)
	{
		this.deleteTime = time;
		Battle.Util.save(this);
	}

	public UUID getId()
	{
		return this.id;
	}

	public double getRange()
	{
		int base = Configs.getSettingInt("battles.min_range");
		int per = 5;
		if(involvedPlayers.size() > 2) return base + (per * (involvedPlayers.size() - 2));
		return base;
	}

	public boolean isActive()
	{
		return this.active;
	}

	public long getDuration()
	{
		long base = Configs.getSettingInt("battles.min_duration") * 1000;
		long per = Configs.getSettingInt("battles.duration_multiplier") * 1000;
		if(involvedPlayers.size() > 2) return base + (per * (involvedPlayers.size() - 2));
		return base;
	}

	public int getMinKills()
	{
		int base = Configs.getSettingInt("battles.min_kills");
		int per = 2;
		if(involvedPlayers.size() > 2) return base + (per * (involvedPlayers.size() - 2));
		return base;
	}

	public int getMaxKills()
	{
		int base = Configs.getSettingInt("battles.max_kills");
		int per = 3;
		if(involvedPlayers.size() > 2) return base + (per * (involvedPlayers.size() - 2));
		return base;
	}

	public Location getStartLocation()
	{
		return DLocation.Util.load(this.startLoc).toLocation();
	}

	public long getStartTime()
	{
		return this.startTime;
	}

	public long getDeleteTime()
	{
		return this.deleteTime;
	}

	void setStarter(DCharacter character)
	{
		this.startedBy = character.getId();
		addParticipant(character);
	}

	public void addParticipant(Participant participant)
	{
		if(participant instanceof DCharacter) this.involvedPlayers.add((participant.getId().toString()));
		else this.involvedTameable.add(participant.getId().toString());
		Battle.Util.save(this);
	}

	public void removeParticipant(Participant participant)
	{
		if(participant instanceof DCharacter) this.involvedPlayers.remove((participant.getId().toString()));
		else this.involvedTameable.remove(participant.getId().toString());
		Battle.Util.save(this);
	}

	public void addKill(Participant participant)
	{
		this.killCounter += 1;
		DCharacter character = participant.getRelatedCharacter();
		if(this.kills.containsKey(character.getId().toString())) this.kills.put(character.getId().toString(), Integer.parseInt(this.kills.get(character.getId().toString()).toString()) + 1);
		else this.kills.put(character.getId().toString(), 1);
		Battle.Util.save(this);
	}

	public void addDeath(Participant participant)
	{
		DCharacter character = participant.getRelatedCharacter();
		if(this.deaths.containsKey(character.getId().toString())) this.deaths.put(character.getId().toString(), Integer.parseInt(this.deaths.get(character.getId().toString()).toString()) + 1);
		else this.deaths.put(character.getId().toString(), 1);
		Battle.Util.save(this);
	}

	public DCharacter getStarter()
	{
		return DCharacter.Util.load(startedBy);
	}

	public Set<Participant> getParticipants()
	{
		return Sets.filter(Sets.union(Sets.newHashSet(Collections2.transform(involvedPlayers, new Function<String, Participant>()
		{
			@Override
			public Participant apply(String character)
			{
				return DCharacter.Util.load(UUID.fromString(character));
			}
		})), Sets.newHashSet(Collections2.transform(involvedTameable, new Function<String, Participant>()
		{
			@Override
			public Participant apply(String tamable)
			{
				return Pet.Util.load(UUID.fromString(tamable));
			}
		}))), new Predicate<Participant>()
		{
			@Override
			public boolean apply(@Nullable Participant participant)
			{
				return participant != null && participant.getRelatedCharacter() != null;
			}
		});
	}

	public Collection<Alliance> getInvolvedAlliances()
	{
		Set<Alliance> set = Sets.newHashSet();
		for(Participant participant : getParticipants())
			set.add(participant.getRelatedCharacter().getAlliance());
		return set;
	}

	public int getKills(Participant participant)
	{
		try
		{
			return Integer.parseInt(kills.get(participant.getId().toString()).toString());
		}
		catch(Exception ignored)
		{}
		return 0;
	}

	public int getDeaths(Participant participant)
	{
		try
		{
			return Integer.parseInt(deaths.get(participant.getId().toString()).toString());
		}
		catch(Exception ignored)
		{}
		return 0;
	}

	public Map<UUID, Integer> getScores()
	{
		Map<UUID, Integer> score = Maps.newHashMap();
		for(Map.Entry<String, Object> entry : kills.entrySet())
		{
			if(!getParticipants().contains(DCharacter.Util.load(UUID.fromString(entry.getKey())))) continue;
			score.put(UUID.fromString(entry.getKey()), Integer.parseInt(entry.getValue().toString()));
		}
		for(Map.Entry<String, Object> entry : deaths.entrySet())
		{
			int base = 0;
			if(score.containsKey(UUID.fromString(entry.getKey()))) base = score.get(UUID.fromString(entry.getKey()));
			score.put(UUID.fromString(entry.getKey()), base - Integer.parseInt(entry.getValue().toString()));
		}
		return score;
	}

	public int getScore(final Alliance alliance)
	{
		Map<UUID, Integer> score = Maps.newHashMap();
		for(Map.Entry<String, Object> entry : kills.entrySet())
		{
			if(!getParticipants().contains(DCharacter.Util.load(UUID.fromString(entry.getKey())))) continue;
			score.put(UUID.fromString(entry.getKey()), Integer.parseInt(entry.getValue().toString()));
		}
		for(Map.Entry<String, Object> entry : deaths.entrySet())
		{
			int base = 0;
			if(score.containsKey(UUID.fromString(entry.getKey()))) base = score.get(UUID.fromString(entry.getKey()));
			score.put(UUID.fromString(entry.getKey()), base - Integer.parseInt(entry.getValue().toString()));
		}
		int sum = 0;
		for(int i : Collections2.transform(Collections2.filter(score.entrySet(), new Predicate<Map.Entry<UUID, Integer>>()
		{
			@Override
			public boolean apply(Map.Entry<UUID, Integer> entry)
			{
				return DCharacter.Util.load(entry.getKey()).getAlliance().getName().equalsIgnoreCase(alliance.getName());
			}
		}), new Function<Map.Entry<UUID, Integer>, Integer>()
		{
			@Override
			public Integer apply(Map.Entry<UUID, Integer> entry)
			{
				return entry.getValue();
			}
		}))
			sum += i;
		return sum;
	}

	public Collection<DCharacter> getMVPs()
	{
		final int max = Collections.max(getScores().values());
		return Collections2.transform(Collections2.filter(getScores().entrySet(), new Predicate<Map.Entry<UUID, Integer>>()
		{
			@Override
			public boolean apply(Map.Entry<UUID, Integer> entry)
			{
				return entry.getValue() == max;
			}
		}), new Function<Map.Entry<UUID, Integer>, DCharacter>()
		{
			@Override
			public DCharacter apply(Map.Entry<UUID, Integer> entry)
			{
				return DCharacter.Util.load(entry.getKey());
			}
		});
	}

	public int getKillCounter()
	{
		return this.killCounter;
	}

	public void end() // TODO Make this specify that it was a pet that won/lost a duel
	{
		for(String stringId : involvedPlayers)
			DataManager.saveTimed(stringId, "just_finished_battle", true, 60);

		Map<UUID, Integer> scores = getScores();
		List<UUID> participants = Lists.newArrayList(scores.keySet());
		if(participants.size() == 2)
		{
			if(scores.get(participants.get(0)).equals(scores.get(participants.get(1))))
			{
				DCharacter one = DCharacter.Util.load(participants.get(0));
				DCharacter two = DCharacter.Util.load(participants.get(1));
				Messages.broadcast(one.getDeity().getColor() + one.getName() + ChatColor.GRAY + " and " + two.getDeity().getColor() + two.getName() + ChatColor.GRAY + " just tied in a duel.");
			}
			else
			{
				int winnerIndex = scores.get(participants.get(0)) > scores.get(participants.get(1)) ? 0 : 1;
				DCharacter winner = DCharacter.Util.load(participants.get(winnerIndex));
				DCharacter loser = DCharacter.Util.load(participants.get(winnerIndex == 0 ? 1 : 0));
				Messages.broadcast(winner.getDeity().getColor() + winner.getName() + ChatColor.GRAY + " just won in a duel against " + loser.getDeity().getColor() + loser.getName() + ChatColor.GRAY + ".");
			}
		}
		else if(participants.size() > 2)
		{
			Alliance winningAlliance = null;
			int winningScore = 0;
			Collection<DCharacter> MVPs = getMVPs();
			boolean oneMVP = MVPs.size() == 1;
			for(Alliance alliance : getInvolvedAlliances())
			{
				int score = getScore(alliance);
				if(getScore(alliance) > winningScore)
				{
					winningAlliance = alliance;
					winningScore = score;
				}
			}
			if(winningAlliance != null)
			{
				Messages.broadcast(ChatColor.GRAY + "The " + ChatColor.YELLOW + winningAlliance.getName() + "s " + ChatColor.GRAY + "just won a battle involving " + involvedPlayers.size() + " participants.");
				Messages.broadcast(ChatColor.GRAY + "The " + ChatColor.YELLOW + "MVP" + (oneMVP ? "" : "s") + ChatColor.GRAY + " from this battle " + (oneMVP ? "is" : "are") + ":");
				for(DCharacter mvp : MVPs)
					Messages.broadcast(" " + ChatColor.DARK_GRAY + Symbol.RIGHTWARD_ARROW + " " + mvp.getDeity().getColor() + mvp.getName() + ChatColor.GRAY + " / " + ChatColor.YELLOW + "Kills" + ChatColor.GRAY + ": " + getKills(mvp) + " / " + ChatColor.YELLOW + "Deaths" + ChatColor.GRAY + ": " + getDeaths(mvp));
			}
		}

		// Reset scoreboards
		resetScoreboards();

		// Remind of cooldown
		sendMessage(ChatColor.YELLOW + "You are safe for 60 seconds.");

		// Prepare for graceful delete
		setDeleteTime(System.currentTimeMillis() + 3000L);
		setInactive();
	}

	public void delete()
	{
		DataManager.battles.remove(getId());
	}

	public void sendMessage(String message)
	{
		for(String stringId : involvedPlayers)
		{
			OfflinePlayer offlinePlayer = DCharacter.Util.load(UUID.fromString(stringId)).getOfflinePlayer();
			if(offlinePlayer.isOnline()) offlinePlayer.getPlayer().sendMessage(message);
		}
	}

	public void startScoreboardRunnable()
	{
		final Battle battle = this;

		runnableId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Demigods.PLUGIN, new BukkitRunnable()
		{
			@Override
			public void run()
			{
				// TODO: This loop could cause some lag
				for(String stringId : involvedPlayers)
				{
					OfflinePlayer offlinePlayer = DCharacter.Util.load(UUID.fromString(stringId)).getOfflinePlayer();
					if(offlinePlayer.isOnline()) Battle.Util.updateScoreboard(offlinePlayer.getPlayer(), battle);
				}
			}
		}, 20, 20);
	}

	public void resetScoreboards()
	{
		// Cancel the runnable
		Bukkit.getScheduler().cancelTask(runnableId);

		// Clear the scoreboards
		for(String stringId : involvedPlayers)
		{
			OfflinePlayer offlinePlayer = DCharacter.Util.load(UUID.fromString(stringId)).getOfflinePlayer();
			if(offlinePlayer.isOnline()) offlinePlayer.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
	}

	public static class Util
	{
		public static void save(Battle battle)
		{
			DataManager.battles.put(battle.getId(), battle);
		}

		public static Battle create(Participant damager, Participant damaged)
		{
			Battle battle = new Battle();
			battle.generateId();
			battle.setStartLocation(damager.getCurrentLocation().toVector().getMidpoint(damaged.getCurrentLocation().toVector()).toLocation(damager.getCurrentLocation().getWorld()));
			battle.setStartTime(System.currentTimeMillis());
			battle.setActive();
			battle.setStarter(damager.getRelatedCharacter());
			battle.addParticipant(damager);
			battle.addParticipant(damaged);
			battle.startScoreboardRunnable();
			save(battle);
			return battle;
		}

		public static Battle get(UUID id)
		{
			return DataManager.battles.get(id);
		}

		public static Set<Battle> getAll()
		{
			return Sets.newHashSet(DataManager.battles.values());
		}

		public static List<Battle> getAllActive()
		{
			return Lists.newArrayList(Collections2.filter(getAll(), new Predicate<Battle>()
			{
				@Override
				public boolean apply(Battle battle)
				{
					return battle.isActive();
				}
			}));
		}

		public static List<Battle> getAllInactive()
		{
			return Lists.newArrayList(Collections2.filter(getAll(), new Predicate<Battle>()
			{
				@Override
				public boolean apply(Battle battle)
				{
					return !battle.isActive();
				}
			}));
		}

		public static boolean existsInRadius(Location location)
		{
			return getInRadius(location) != null;
		}

		public static Battle getInRadius(final Location location)
		{
			try
			{
				return Iterators.find(getAllActive().iterator(), new Predicate<Battle>()
				{
					@Override
					public boolean apply(Battle battle)
					{
						return battle.getStartLocation().distance(location) <= battle.getRange();
					}
				});
			}
			catch(NoSuchElementException ignored)
			{}
			return null;
		}

		public static boolean isInBattle(final Participant participant)
		{
			return Iterators.any(getAllActive().iterator(), new Predicate<Battle>()
			{
				@Override
				public boolean apply(Battle battle)
				{
					return battle.getParticipants().contains(participant);
				}
			});
		}

		public static Battle getBattle(final Participant participant)
		{
			try
			{
				return Iterators.find(getAllActive().iterator(), new Predicate<Battle>()
				{
					@Override
					public boolean apply(Battle battle)
					{
						return battle.getParticipants().contains(participant);
					}
				});
			}
			catch(NoSuchElementException ignored)
			{}
			return null;
		}

		public static boolean existsNear(Location location)
		{
			return getNear(location) != null;
		}

		public static Battle getNear(final Location location)
		{
			try
			{
				return Iterators.find(getAllActive().iterator(), new Predicate<Battle>()
				{
					@Override
					public boolean apply(Battle battle)
					{
						double distance = battle.getStartLocation().distance(location);
						return distance > battle.getRange() && distance <= Configs.getSettingInt("battles.merge_range");
					}
				});
			}
			catch(NoSuchElementException ignored)
			{}
			return null;
		}

		public static Collection<Location> battleBorder(final Battle battle)
		{
			if(!Demigods.Util.isRunningSpigot()) throw new SpigotNotFoundException();
			return Collections2.transform(DLocation.Util.getCirclePoints(battle.getStartLocation(), battle.getRange(), 120), new Function<Location, Location>()
			{
				@Override
				public Location apply(Location point)
				{
					return new Location(point.getWorld(), point.getBlockX(), point.getWorld().getHighestBlockYAt(point), point.getBlockZ());
				}
			});
		}

		/*
		 * This is completely broken. TODO
		 */
		public static Location randomRespawnPoint(Battle battle)
		{
			List<Location> respawnPoints = getSafeRespawnPoints(battle);
			if(respawnPoints.size() == 0) return battle.getStartLocation();

			Location target = respawnPoints.get(Randoms.generateIntRange(0, respawnPoints.size() - 1));

			Vector direction = target.toVector().subtract(battle.getStartLocation().toVector()).normalize();
			double X = direction.getX();
			double Y = direction.getY();
			double Z = direction.getZ();

			// Now change the angle
			Location changed = target.clone();
			changed.setYaw(180 - DLocation.Util.toDegree(Math.atan2(Y, X)));
			changed.setPitch(90 - DLocation.Util.toDegree(Math.acos(Z)));
			return changed;
		}

		/*
		 * This is completely broken. TODO
		 */
		public static boolean isSafeLocation(Location reference, Location checking)
		{
			if(checking.getBlock().getType().isSolid() || checking.getBlock().getType().equals(Material.LAVA)) return false;
			double referenceY = reference.getY();
			double checkingY = checking.getY();
			return Math.abs(referenceY - checkingY) <= 5;
		}

		public static List<Location> getSafeRespawnPoints(final Battle battle)
		{
			return Lists.newArrayList(Collections2.filter(Collections2.transform(DLocation.Util.getCirclePoints(battle.getStartLocation(), battle.getRange() - 1.5, 100), new Function<Location, Location>()
			{
				@Override
				public Location apply(Location point)
				{
					return new Location(point.getWorld(), point.getBlockX(), point.getWorld().getHighestBlockYAt(point), point.getBlockZ());
				}
			}), new Predicate<Location>()
			{
				@Override
				public boolean apply(Location location)
				{
					return isSafeLocation(battle.getStartLocation(), location);
				}
			}));
		}

		public static boolean canParticipate(Entity entity)
		{
			if(entity instanceof Player)
			{
				DCharacter character = DPlayer.Util.getPlayer((Player) entity).getCurrent();
				return character != null && !character.getDeity().getFlags().contains(Deity.Flag.NO_BATTLE);
			}
			return entity instanceof Tameable && Pet.Util.getPet((LivingEntity) entity) != null && isInBattle(Pet.Util.getPet((LivingEntity) entity).getRelatedCharacter());
		}

		public static Participant defineParticipant(Entity entity)
		{
			if(!canParticipate(entity)) return null;
			if(entity instanceof Player) return DPlayer.Util.getPlayer((Player) entity).getCurrent();
			return Pet.Util.getPet((LivingEntity) entity);
		}

		public static void battleDeath(Participant damager, Participant damagee, Battle battle)
		{
			if(damager instanceof DCharacter) ((DCharacter) damager).addKill();
			if(damager.getRelatedCharacter().getOfflinePlayer().isOnline()) damager.getRelatedCharacter().getOfflinePlayer().getPlayer().sendMessage(ChatColor.GREEN + "+1 Kill.");
			battle.addKill(damager);
			damagee.getEntity().setHealth(damagee.getEntity().getMaxHealth());
			Vehicles.teleport(damagee.getEntity(), randomRespawnPoint(battle));
			if(damagee instanceof DCharacter)
			{
				DCharacter character = (DCharacter) damagee;
				Player player = character.getOfflinePlayer().getPlayer();
				player.setFoodLevel(20);
				for(PotionEffect potionEffect : player.getActivePotionEffects())
					player.removePotionEffect(potionEffect.getType());
				character.setPotionEffects(player.getActivePotionEffects());
				character.addDeath(damager.getRelatedCharacter());
			}
			if(damagee.getRelatedCharacter().getOfflinePlayer().isOnline()) damagee.getRelatedCharacter().getOfflinePlayer().getPlayer().sendMessage(ChatColor.RED + "+1 Death.");
			battle.addDeath(damagee);
		}

		public static void battleDeath(Participant damagee, Battle battle)
		{
			damagee.getEntity().setHealth(damagee.getEntity().getMaxHealth());
			damagee.getEntity().teleport(randomRespawnPoint(battle));
			if(damagee instanceof DCharacter) ((DCharacter) damagee).addDeath();
			if(damagee.getRelatedCharacter().getOfflinePlayer().isOnline()) damagee.getRelatedCharacter().getOfflinePlayer().getPlayer().sendMessage(ChatColor.RED + "+1 Death.");
			battle.addDeath(damagee);
		}

		public static boolean canTarget(Entity entity)
		{
			return !canParticipate(entity) || canParticipate(entity) && canTarget(defineParticipant(entity));
		}

		/**
		 * Returns true if doTargeting is allowed for <code>player</code>.
		 * 
		 * @param participant the player to check.
		 * @return true/false depending on if doTargeting is allowed.
		 */
		public static boolean canTarget(Participant participant) // TODO REDO THIS
		{
			return participant == null || participant.canPvp() || participant.getCurrentLocation() != null && !Structure.Util.isInRadiusWithFlag(participant.getCurrentLocation(), Structure.Flag.NO_PVP);
		}

		/**
		 * Updates all battle particles. Meant for use in a Runnable.
		 */
		public static void updateBattleParticles()
		{
			for(Battle battle : Battle.Util.getAllActive())
				for(Location point : Battle.Util.battleBorder(battle))
					point.getWorld().playEffect(point, Effect.MOBSPAWNER_FLAMES, 0, (int) (battle.getRange() * 2.5));
		}

		/**
		 * Updates all battles.
		 */
		public static void updateBattles()
		{
			// End all active battles that should end.
			for(Battle battle : Collections2.filter(Battle.Util.getAllActive(), new Predicate<Battle>()
			{
				@Override
				public boolean apply(Battle battle)
				{
					return battle.getKillCounter() >= battle.getMaxKills() || battle.getStartTime() + battle.getDuration() <= System.currentTimeMillis() && battle.getKillCounter() >= battle.getMinKills() || battle.getParticipants().size() < 2 || battle.getInvolvedAlliances().size() < 2;
				}
			}))
			{
				battle.end();
				Skill.Util.processBattle(battle);
			}

			// Delete all inactive battles that should be deleted.
			for(Battle battle : Collections2.filter(Battle.Util.getAllInactive(), new Predicate<Battle>()
			{
				@Override
				public boolean apply(Battle battle)
				{
					return battle.getDeleteTime() >= System.currentTimeMillis();
				}
			}))
				battle.delete();
		}

		/**
		 * Updates the scoreboard for the given <code>player</code> with information from the <code>battle</code>.
		 * 
		 * @param player the player to give the scoreboard to.
		 * @param battle the battle to grab stats from.
		 */
		public static void updateScoreboard(Player player, Battle battle)
		{
			// Define variables
			Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

			// Define objective
			Objective info = scoreboard.registerNewObjective("battle_info", "dummy");
			info.setDisplaySlot(DisplaySlot.SIDEBAR);
			info.setDisplayName(ChatColor.AQUA + "Current Battle Stats");

			// Add the information
			Score kills = info.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Total Kills"));
			kills.setScore(battle.getKillCounter());

			Score neededKills = info.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Kills Needed"));
			neededKills.setScore(battle.getMinKills());

			for(Alliance alliance : battle.getInvolvedAlliances())
			{
				Score allianceKills = info.getScore(Bukkit.getOfflinePlayer(ChatColor.YELLOW + alliance.getName() + ChatColor.GRAY + " Score"));
				allianceKills.setScore(battle.getScore(alliance));
			}

			Score participants = info.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Participants"));
			participants.setScore(battle.involvedPlayers.size());

			Score points = info.getScore(Bukkit.getOfflinePlayer(ChatColor.GRAY + "Duration"));
			points.setScore((int) (System.currentTimeMillis() - battle.getStartTime()) / 1000);

			player.setScoreboard(scoreboard);
		}
	}
}
