package com.censoredsoftware.demigods.episodes.demo.ability.passive;

import com.censoredsoftware.core.util.Randoms;
import com.censoredsoftware.demigods.engine.element.Ability;
import com.censoredsoftware.demigods.engine.player.Pet;
import com.google.common.collect.Lists;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class RainbowHorse extends Ability
{
	private final static String name = "Horse of a Different Color", command = null;
	private final static int cost = 0, delay = 0, repeat = 100;
	private static Info info;
	private final static List<String> details = new ArrayList<String>(1)
	{
		{
			add("All of you horse are belong to us.");
		}
	};
	private final static Devotion.Type type = Devotion.Type.PASSIVE;

	public RainbowHorse(String deity, String permission)
	{
		super(info = new Info(deity, name, command, permission, cost, delay, repeat, details, type), null, new BukkitRunnable()
		{
			@Override
			public void run()
			{
				for(Pet horse : Pet.Util.findByType(EntityType.HORSE))
				{
					if(horse.getDeity().getInfo().getName().equals("DrD1sco") && horse.getEntity() != null && !horse.getEntity().isDead())
					{
						((Horse) horse.getEntity()).setColor(getRandomHorseColor());
					}
				}
			}

			private Horse.Color getRandomHorseColor()
			{
				return Lists.newArrayList(Horse.Color.values()).get(Randoms.generateIntRange(0, 5));
			}
		});
	}
}
