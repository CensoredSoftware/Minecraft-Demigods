package com.censoredsoftware.demigods.greek.ability.passive;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.censoredsoftware.censoredlib.util.Randoms;
import com.censoredsoftware.demigods.engine.ability.Ability;
import com.censoredsoftware.demigods.engine.player.DPet;
import com.censoredsoftware.demigods.engine.player.Skill;
import com.censoredsoftware.demigods.engine.util.Zones;
import com.google.common.collect.Lists;

public class RainbowHorse implements Ability
{
	private final static String name = "Horse of a Different Color", command = null;
	private final static int cost = 0, delay = 0, repeat = 100;
	private final static List<String> details = Lists.newArrayList("All of you horse are belong to us.");
	private String deity, permission;
	private final static Skill.Type type = Skill.Type.PASSIVE;

	public RainbowHorse(String deity, String permission)
	{
		this.deity = deity;
		this.permission = permission;
	}

	@Override
	public String getDeity()
	{
		return deity;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getCommand()
	{
		return command;
	}

	@Override
	public String getPermission()
	{
		return permission;
	}

	@Override
	public int getCost()
	{
		return cost;
	}

	@Override
	public int getDelay()
	{
		return delay;
	}

	@Override
	public int getRepeat()
	{
		return repeat;
	}

	@Override
	public List<String> getDetails()
	{
		return details;
	}

	@Override
	public Skill.Type getType()
	{
		return type;
	}

	@Override
	public Material getWeapon()
	{
		return null;
	}

	@Override
	public boolean hasWeapon()
	{
		return getWeapon() != null;
	}

	@Override
	public Listener getListener()
	{
		return null;
	}

	@Override
	public BukkitRunnable getRunnable()
	{
		return new BukkitRunnable()
		{
			@Override
			public void run()
			{
				for(DPet horse : DPet.Util.findByType(EntityType.HORSE))
				{
					if(horse.getCurrentLocation() == null || Zones.inNoDemigodsZone(horse.getCurrentLocation())) return;
					if(horse.getDeity().getName().equals("DrD1sco") && horse.getEntity() != null && !horse.getEntity().isDead()) ((Horse) horse.getEntity()).setColor(getRandomHorseColor());
				}
			}

			private Horse.Color getRandomHorseColor()
			{
				return Lists.newArrayList(Horse.Color.values()).get(Randoms.generateIntRange(0, 5));
			}
		};
	}
}
