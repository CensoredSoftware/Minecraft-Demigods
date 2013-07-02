package com.censoredsoftware.Demigods.Episodes.Demo.Deity.Titan;

import com.censoredsoftware.Demigods.Engine.Demigods;
import com.censoredsoftware.Demigods.Engine.Object.Ability.Ability;
import com.censoredsoftware.Demigods.Engine.Object.Ability.AbilityInfo;
import com.censoredsoftware.Demigods.Engine.Object.Ability.Devotion;
import com.censoredsoftware.Demigods.Engine.Object.Deity.Deity;
import com.censoredsoftware.Demigods.Engine.Object.Deity.DeityInfo;
import com.censoredsoftware.Demigods.Engine.Object.General.DemigodsPlayer;
import com.censoredsoftware.Demigods.Engine.Object.PlayerCharacter.PlayerCharacter;
import com.censoredsoftware.Demigods.Engine.Utility.UnicodeUtility;
import com.censoredsoftware.Demigods.Engine.Utility.ZoneUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import java.util.*;

public class Prometheus extends Deity
{
	private static String name = "Prometheus", alliance = "Titan";
	private static ChatColor color = ChatColor.GOLD;
	private static Set<Material> claimItems = new HashSet<Material>()
	{
		{
			add(Material.DIRT);
		}
	};
	private static List<String> lore = new ArrayList<String>()
	{
		{
			add(" ");
			add(ChatColor.AQUA + " Demigods > " + ChatColor.RESET + color + name);
			add(ChatColor.RESET + "-----------------------------------------------------");
			add(ChatColor.YELLOW + " Claim Items:");
			for(Material item : claimItems)
			{
				add(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.WHITE + item.name());
			}
			add(ChatColor.YELLOW + " Abilities:");
		}
	};
	private static Type type = Type.DEMO;
	private static Set<Ability> abilities = new HashSet<Ability>()
	{
		{
			add(new ShootFireball());
			add(new Blaze());
			add(new Firestorm());
		}
	};

	public Prometheus()
	{
		super(new DeityInfo(name, alliance, color, claimItems, lore, type), abilities);
	}

	public static void shootFireball(Location from, Location to, Player shooter)
	{
		Fireball fireball = (Fireball) shooter.getWorld().spawnEntity(from, EntityType.FIREBALL);
		to.setX(to.getX() + .5);
		to.setY(to.getY() + .5);
		to.setZ(to.getZ() + .5);
		Vector path = to.toVector().subtract(from.toVector());
		Vector victor = from.toVector().add(from.getDirection().multiply(2));
		fireball.teleport(new Location(shooter.getWorld(), victor.getX(), victor.getY(), victor.getZ()));
		fireball.setDirection(path);
		fireball.setShooter(shooter);
	}
}

class ShootFireball extends Ability
{
	private static String deity = "Prometheus", name = "Fireball", command = "fireball", permission = "demigods.titan.protmetheus";
	private static int cost = 100, delay = 5, cooldownMin = 0, cooldownMax = 0;
	private static AbilityInfo info;
	private static List<String> details = new ArrayList<String>()
	{
		{
			add(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.GREEN + "/fireball" + ChatColor.WHITE + " - Shoot a fireball at the cursor's location.");
		}
	};
	private static Devotion.Type type = Devotion.Type.OFFENSE;

	protected ShootFireball()
	{
		super(info = new AbilityInfo(deity, name, command, permission, cost, delay, cooldownMin, cooldownMax, details, type), new Listener()
		{
			@EventHandler(priority = EventPriority.HIGH)
			public void onPlayerInteract(PlayerInteractEvent interactEvent)
			{
				if(!Ability.isLeftClick(interactEvent)) return;

				// Set variables
				Player player = interactEvent.getPlayer();
				PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();

				if(!Deity.canUseDeitySilent(player, deity)) return;

				if(character.getMeta().isEnabledAbility(name) || ((player.getItemInHand() != null) && (player.getItemInHand().getType() == character.getMeta().getBind(name))))
				{
					if(!PlayerCharacter.isCooledDown(character, name, false)) return;

					fireball(player);
				}
			}
		});
	}

	// The actual ability command
	public static void fireball(Player player)
	{
		// Define variables
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();
		LivingEntity target = Ability.autoTarget(player);

		if(!Ability.doAbilityPreProcess(player, target, "fireball", cost, info)) return;
		PlayerCharacter.setCoolDown(character, name, System.currentTimeMillis() + delay);
		character.getMeta().subtractFavor(cost);

		if(!Ability.doTargeting(player, target)) return;

		if(target.getEntityId() != player.getEntityId())
		{
			Prometheus.shootFireball(player.getEyeLocation(), target.getLocation(), player);
		}
	}
}

class Blaze extends Ability
{
	private static String deity = "Prometheus", name = "Blaze", command = "blaze", permission = "demigods.titan.protmetheus";
	private static int cost = 400, delay = 15, cooldownMin = 0, cooldownMax = 0;
	private static AbilityInfo info;
	private static List<String> details = new ArrayList<String>()
	{
		{
			add(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.GREEN + "/blaze" + ChatColor.WHITE + " - Ignite the ground at the target location.");
		}
	};
	private static Devotion.Type type = Devotion.Type.OFFENSE;

	protected Blaze()
	{
		super(info = new AbilityInfo(deity, name, command, permission, cost, delay, cooldownMin, cooldownMax, details, type), new Listener()
		{
			@EventHandler(priority = EventPriority.HIGH)
			public void onPlayerInteract(PlayerInteractEvent interactEvent)
			{
				if(!Ability.isLeftClick(interactEvent)) return;

				// Set variables
				Player player = interactEvent.getPlayer();
				PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();

				if(!Deity.canUseDeitySilent(player, deity)) return;

				if(character.getMeta().isEnabledAbility(name) || ((player.getItemInHand() != null) && (player.getItemInHand().getType() == character.getMeta().getBind(name))))
				{
					if(!PlayerCharacter.isCooledDown(character, name, false)) return;

					blaze(player);
				}
			}
		});
	}

	// The actual ability command
	public static void blaze(Player player)
	{
		// Define variables
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();
		LivingEntity target = Ability.autoTarget(player);
		int power = character.getMeta().getDevotion(type).getLevel();
		int diameter = (int) Math.ceil(1.43 * Math.pow(power, 0.1527));
		if(diameter > 12) diameter = 12;

		if(!Ability.doAbilityPreProcess(player, target, name, cost, info)) return;
		PlayerCharacter.setCoolDown(character, name, System.currentTimeMillis() + delay);
		character.getMeta().subtractFavor(cost);

		if(!Ability.doTargeting(player, target)) return;

		if(target.getEntityId() != player.getEntityId())
		{
			for(int X = -diameter / 2; X <= diameter / 2; X++)
			{
				for(int Y = -diameter / 2; Y <= diameter / 2; Y++)
				{
					for(int Z = -diameter / 2; Z <= diameter / 2; Z++)
					{
						Block block = target.getWorld().getBlockAt(target.getLocation().getBlockX() + X, target.getLocation().getBlockY() + Y, target.getLocation().getBlockZ() + Z);
						if((block.getType() == Material.AIR) || (((block.getType() == Material.SNOW)) && !ZoneUtility.zoneNoBuild(player, block.getLocation()))) block.setType(Material.FIRE);
					}
				}
			}
		}
	}
}

class Firestorm extends Ability
{
	private static String deity = "Prometheus", name = "Firestorm", command = "firestorm", permission = "demigods.titan.protmetheus.ultimate";
	private static int cost = 5500, delay = 15, cooldownMin = 60, cooldownMax = 600;
	private static AbilityInfo info;
	private static List<String> details = new ArrayList<String>()
	{
		{
			add(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.GREEN + "/firestorm" + ChatColor.WHITE + " - Rain down fireballs from the sky.");
		}
	};
	private static Devotion.Type type = Devotion.Type.ULTIMATE;

	protected Firestorm()
	{
		super(info = new AbilityInfo(deity, name, command, permission, cost, delay, cooldownMin, cooldownMax, details, type), new Listener()
		{
			@EventHandler(priority = EventPriority.HIGH)
			public void onPlayerInteract(PlayerInteractEvent interactEvent)
			{
				if(!Ability.isLeftClick(interactEvent)) return;

				// Set variables
				Player player = interactEvent.getPlayer();
				PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();

				if(!Deity.canUseDeitySilent(player, deity)) return;

				if(character.getMeta().isEnabledAbility(name) || ((player.getItemInHand() != null) && (player.getItemInHand().getType() == character.getMeta().getBind(name))))
				{
					if(!PlayerCharacter.isCooledDown(character, name, false)) return;

					firestorm(player);
				}
			}
		});
	}

	// The actual ability command
	public static void firestorm(final Player player)
	{
		// Define variables
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();

		if(!Ability.doAbilityPreProcess(player, name, cost, info)) return;

		int total = 3 * ((int) Math.pow(character.getMeta().getAscensions(), 0.35));
		Deque<LivingEntity> entities = new ArrayDeque<LivingEntity>();

		for(final Entity entity : player.getNearbyEntities(50, 50, 50)) // TODO: Make this dependent on levels.
		{
			// Validate them first
			if(!(entity instanceof LivingEntity)) continue;
			if(entity instanceof Player)
			{
				PlayerCharacter otherCharacter = DemigodsPlayer.getPlayer((Player) entity).getCurrent();
				if(otherCharacter != null && PlayerCharacter.areAllied(character, otherCharacter)) continue;
			}
			if(!ZoneUtility.canTarget(entity)) continue;

			entities.add((LivingEntity) entity);
		}

		// Now shoot them
		for(int i = 0; i <= total; i++)
		{
			for(final LivingEntity entity : entities)
			{
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Demigods.plugin, new Runnable()
				{
					@Override
					public void run()
					{
						Location entityLocation = entity.getLocation();
						Location air = new Location(entityLocation.getWorld(), entityLocation.getX(), entityLocation.getWorld().getHighestBlockAt(entityLocation).getLocation().getY() + 10.0, entityLocation.getZ());
						Location ground = new Location(entityLocation.getWorld(), entityLocation.getX(), entityLocation.getY(), entityLocation.getZ());
						Prometheus.shootFireball(air, ground, player);
					}
				}, 40);
			}
		}
	}
}
