package com.censoredsoftware.demigods.item;

import com.censoredsoftware.demigods.util.Items;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public enum Baetylus
{
	/**
	 * A large shard of the Baetylus.
	 */
	LARGE_SHARD(new Shard(20.0, Sound.ANVIL_BREAK, Items.create(Material.GHAST_TEAR, "Large Baetylus Shard", new ArrayList<String>(1)
	{
		{
			add("Regain full health instead of dying.");
		}
	}, new HashMap<Enchantment, Integer>(1)
	{
		{
			put(Enchantment.THORNS, 3);
		}
	}))),

	/**
	 * A shard of the Baetylus.
	 */
	NORMAL_SHARD(new Shard(10.0, Sound.ANVIL_BREAK, Items.create(Material.GHAST_TEAR, "Baetylus Shard", new ArrayList<String>(1)
	{
		{
			add("Regain half health instead of dying.");
		}
	}, new HashMap<Enchantment, Integer>(1)
	{
		{
			put(Enchantment.THORNS, 2);
		}
	}))),

	/**
	 * A small shard of the Baetylus.
	 */
	SMALL_SHARD(new Shard(5.0, Sound.ANVIL_BREAK, Items.create(Material.GHAST_TEAR, "Small Baetylus Shard", new ArrayList<String>(1)
	{
		{
			add("Regain quarter health instead of dying.");
		}
	}, new HashMap<Enchantment, Integer>(1)
	{
		{
			put(Enchantment.THORNS, 1);
		}
	})));

	private final Shard value;

	private Baetylus(Shard value)
	{
		this.value = value;
	}

	public Shard getShard()
	{
		return this.value;
	}

	public static class Shard
	{
		private final double health;
		private final Sound sound;
		private final ItemStack item;

		public Shard(double health, Sound sound, ItemStack item)
		{
			this.health = health;
			this.sound = sound;
			this.item = item;
		}

		public double getHealth()
		{
			return health;
		}

		public Sound getSound()
		{
			return sound;
		}

		public ItemStack getItem()
		{
			return item;
		}
	}

	public static class Util
	{
		// TODO
	}
}
