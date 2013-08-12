package com.censoredsoftware.demigods.deity.god;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import com.censoredsoftware.core.util.Unicodes;
import com.censoredsoftware.demigods.ability.Ability;
import com.censoredsoftware.demigods.ability.offense.Shove;
import com.censoredsoftware.demigods.ability.passive.NoFall;
import com.censoredsoftware.demigods.ability.ultimate.Storm;
import com.censoredsoftware.demigods.deity.Deity;

public class Zeus extends Deity
{
	private final static String name = "Zeus", alliance = "God", permission = "demigods.god.zeus";
	private final static ChatColor color = ChatColor.YELLOW;
	private final static Set<Material> claimItems = new HashSet<Material>(1)
	{
		{
			add(Material.DIRT);
		}
	};
	private final static List<String> lore = new ArrayList<String>()
	{
		{
			add(" ");
			add(ChatColor.AQUA + " Demigods > " + ChatColor.RESET + color + name);
			add(ChatColor.RESET + "-----------------------------------------------------");
			add(ChatColor.YELLOW + " Claim Items:");
			for(Material item : claimItems)
			{
				add(ChatColor.GRAY + " " + Unicodes.rightwardArrow() + " " + ChatColor.WHITE + item.name());
			}
			add(ChatColor.YELLOW + " Abilities:");
		}
	};
	private final static Type type = Type.DEMO;
	private final static Set<Ability> abilities = new HashSet<Ability>(4)
	{
		{
			add(new NoFall(name, permission));
			add(new Shove());
			add(new Storm.Lightning(name, permission));
			add(new Storm(name, permission));
		}
	};

	public Zeus()
	{
		super(name, alliance, permission, color, claimItems, lore, type, abilities);
	}
}