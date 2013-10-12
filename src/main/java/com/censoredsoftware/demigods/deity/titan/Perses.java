package com.censoredsoftware.demigods.deity.titan;

import com.censoredsoftware.demigods.ability.Ability;
import com.censoredsoftware.demigods.ability.offense.Blaze;
import com.censoredsoftware.demigods.ability.passive.NoFire;
import com.censoredsoftware.demigods.ability.ultimate.Firestorm;
import com.censoredsoftware.demigods.deity.Alliance;
import com.censoredsoftware.demigods.deity.Deity;
import com.censoredsoftware.demigods.deity.ListedAlliance;
import com.censoredsoftware.demigods.language.Symbol;
import com.censoredsoftware.demigods.util.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Perses
{
	public final static String name = "Perses", shortDescription = ChatColor.GRAY + "The Titan of anger and destruction.";
	public final static Alliance alliance = ListedAlliance.TITAN;
	public final static String permission = alliance.getPermission() + "." + name.toLowerCase();
	public final static int accuracy = 15, favorRegen = 5, maxFavor = 20000, maxHealth = 30;
	public final static ChatColor color = ChatColor.GOLD;
	public final static Map<Material, Integer> claimItems = Maps.newHashMap(ImmutableMap.of(Material.FLINT, 1));
	public final static Map<Material, Integer> forsakeItems = Maps.newHashMap(ImmutableMap.of(Material.FIREWORK, 4));
	public final static List<String> lore = new ArrayList<String>(9 + claimItems.size())
	{
		{
			add(" ");
			add(ChatColor.RED + " Demigods > " + ChatColor.RESET + color + name);
			add(ChatColor.RESET + "-----------------------------------------------------");
			add(" ");
			add(ChatColor.YELLOW + " Claim Items:");
			add(" ");
			for(Map.Entry<Material, Integer> entry : claimItems.entrySet())
				add(ChatColor.GRAY + " " + Symbol.RIGHTWARD_ARROW + " " + ChatColor.WHITE + entry.getValue() + " " + Strings.beautify(entry.getKey().name()).toLowerCase() + (entry.getValue() > 1 ? "s" : ""));
			add(" ");
			add(ChatColor.YELLOW + " Abilities:");
			add(" ");
		}
	};
	public final static Set<Deity.Flag> flags = Sets.newHashSet(Deity.Flag.MAJOR_DEITY, Deity.Flag.PLAYABLE);
	public final static List<Ability> abilities = Lists.newArrayList(new Firestorm.ShootFireball(name, permission), new Blaze(name, permission), new Firestorm(name, permission), new NoFire(name, permission));
}
