package com.censoredsoftware.demigods.deity.titan;

import com.censoredsoftware.demigods.Elements;
import com.censoredsoftware.demigods.ability.Ability;
import com.censoredsoftware.demigods.ability.offense.Blaze;
import com.censoredsoftware.demigods.ability.ultimate.Firestorm;
import com.censoredsoftware.demigods.deity.Deity;
import com.censoredsoftware.demigods.util.Unicodes;
import com.google.common.collect.Sets;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Prometheus implements Deity
{
	private final static String name = "Prometheus", alliance = "Titan", permission = "demigods.titan.protmetheus";
	private final static ChatColor color = ChatColor.GOLD;
	private final static Set<Material> claimItems = Sets.newHashSet(Material.FLINT);
	private final static List<String> lore = new ArrayList<String>(5 + claimItems.size())
	{
		{
			add(" ");
			add(ChatColor.AQUA + " Demigods > " + ChatColor.RESET + color + name);
			add(ChatColor.RESET + "-----------------------------------------------------");
			add(ChatColor.YELLOW + " Claim Items:");
			for(Material item : claimItems)
				add(ChatColor.GRAY + " " + Unicodes.rightwardArrow() + " " + ChatColor.WHITE + item.name());
			add(ChatColor.YELLOW + " Abilities:");
		}
	};
	private final static Type type = Type.TIER1;
	private final static Set<Ability> abilities = Sets.newHashSet(new Firestorm.ShootFireball(name, permission), new Blaze(name, permission), new Firestorm(name, permission));

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public Elements.ListedDeity getListedDeity()
	{
		return Elements.Deities.PROMETHEUS;
	}

	@Override
	public String getAlliance()
	{
		return alliance;
	}

	@Override
	public String getPermission()
	{
		return permission;
	}

	@Override
	public ChatColor getColor()
	{
		return color;
	}

	@Override
	public Set<Material> getClaimItems()
	{
		return claimItems;
	}

	@Override
	public List<String> getLore()
	{
		return lore;
	}

	@Override
	public Type getType()
	{
		return type;
	}

	@Override
	public Set<Ability> getAbilities()
	{
		return abilities;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
