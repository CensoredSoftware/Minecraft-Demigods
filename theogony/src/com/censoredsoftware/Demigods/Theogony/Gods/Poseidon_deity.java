/*
	Copyright (c) 2013 The Demigods Team
	
	Demigods License v1
	
	This plugin is provided "as is" and without any warranty.  Any express or
	implied warranties, including, but not limited to, the implied warranties
	of merchantability and fitness for a particular purpose are disclaimed.
	In no event shall the authors be liable to any party for any direct,
	indirect, incidental, special, exemplary, or consequential damages arising
	in any way out of the use or misuse of this plugin.
	
	Definitions
	
	 1. This Plugin is defined as all of the files within any archive
	    file or any group of files released in conjunction by the Demigods Team,
	    the Demigods Team, or a derived or modified work based on such files.
	
	 2. A Modification, or a Mod, is defined as this Plugin or a derivative of
	    it with one or more Modification applied to it, or as any program that
	    depends on this Plugin.
	
	 3. Distribution is defined as allowing one or more other people to in
	    any way download or receive a copy of this Plugin, a Modified
	    Plugin, or a derivative of this Plugin.
	
	 4. The Software is defined as an installed copy of this Plugin, a
	    Modified Plugin, or a derivative of this Plugin.
	
	 5. The Demigods Team is defined as Alex Bennett and Alexander Chauncey
	    of http://www.censoredsoftware.com/.
	
	Agreement
	
	 1. Permission is hereby granted to use, copy, modify and/or
	    distribute this Plugin, provided that:
	
	    a. All copyright notices within source files and as generated by
	       the Software as output are retained, unchanged.
	
	    b. Any Distribution of this Plugin, whether as a Modified Plugin
	       or not, includes this license and is released under the terms
	       of this Agreement. This clause is not dependant upon any
	       measure of changes made to this Plugin.
	
	    c. This Plugin, Modified Plugins, and derivative works may not
	       be sold or released under any paid license without explicit 
	       permission from the Demigods Team. Copying fees for the 
	       transport of this Plugin, support fees for installation or
	       other services, and hosting fees for hosting the Software may,
	       however, be imposed.
	
	    d. Any Distribution of this Plugin, whether as a Modified
	       Plugin or not, requires express written consent from the
	       Demigods Team.
	
	 2. You may make Modifications to this Plugin or a derivative of it,
	    and distribute your Modifications in a form that is separate from
	    the Plugin. The following restrictions apply to this type of
	    Modification:
	
	    a. A Modification must not alter or remove any copyright notices
	       in the Software or Plugin, generated or otherwise.
	
	    b. When a Modification to the Plugin is released, a
	       non-exclusive royalty-free right is granted to the Demigods Team
	       to distribute the Modification in future versions of the
	       Plugin provided such versions remain available under the
	       terms of this Agreement in addition to any other license(s) of
	       the initial developer.
	
	    c. Any Distribution of a Modified Plugin or derivative requires
	       express written consent from the Demigods Team.
	
	 3. Permission is hereby also granted to distribute programs which
	    depend on this Plugin, provided that you do not distribute any
	    Modified Plugin without express written consent.
	
	 4. The Demigods Team reserves the right to change the terms of this
	    Agreement at any time, although those changes are not retroactive
	    to past releases, unless redefining the Demigods Team. Failure to
	    receive notification of a change does not make those changes invalid.
	    A current copy of this Agreement can be found included with the Plugin.
	
	 5. This Agreement will terminate automatically if you fail to comply
	    with the limitations described herein. Upon termination, you must
	    destroy all copies of this Plugin, the Software, and any
	    derivatives within 48 hours.
 */

package com.censoredsoftware.Demigods.Theogony.Gods;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.censoredsoftware.Demigods.Demigods;
import com.censoredsoftware.Demigods.Events.Ability.AbilityEvent.AbilityType;
import com.censoredsoftware.Demigods.Objects.Character.PlayerCharacter;
import com.censoredsoftware.Demigods.Theogony.Theogony;

public class Poseidon_deity implements Listener
{
	private static final Demigods API = Theogony.INSTANCE;

	// Create required universal deity variables
	private static final String DEITYNAME = "Poseidon";
	private static final String DEITYALLIANCE = "God";
	private static final ChatColor DEITYCOLOR = ChatColor.AQUA;

	/*
	 * Set deity-specific ability variable(s).
	 */
	// "/reel" Command:
	private static final String REEL_NAME = "Reel"; // Sets the name of this command
	private static long REEL_TIME; // Creates the variable for later use
	private static final int REEL_COST = 120; // Cost to run command in "favor"
	private static final int REEL_DELAY = 1100; // In milliseconds

	// "/drown" Command:
	private static final String DROWN_NAME = "Drown"; // Sets the name of this command
	private static long DROWN_TIME; // Creates the variable for later use
	private static final int DROWN_COST = 240; // Cost to run command in "favor"
	private static final int DROWN_DELAY = 10000; // In milliseconds

	public ArrayList<Material> getClaimItems()
	{
		ArrayList<Material> claimItems = new ArrayList<Material>();

		// Add new items in this format: claimItems.add(Material.NAME_OF_MATERIAL);
		// claimItems.add(Material.WATER_BUCKET);
		// claimItems.add(Material.WATER_LILY);
		claimItems.add(Material.DIRT);

		return claimItems;
	}

	public ArrayList<String> getInfo(Player player)
	{
		ArrayList<String> toReturn = new ArrayList<String>();

		if(API.misc.canUseDeitySilent(player, DEITYNAME))
		{
			toReturn.add(" "); // TODO
			toReturn.add(ChatColor.AQUA + " Demigods > " + ChatColor.RESET + DEITYCOLOR + DEITYNAME);
			toReturn.add(ChatColor.RESET + "-----------------------------------------------------");
			toReturn.add(ChatColor.YELLOW + " Active:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/reel" + ChatColor.WHITE + " - Use a fishing rod for a stronger attack.");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/drown" + ChatColor.WHITE + " - Drown your enemies in sufficating water.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " Passive:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.WHITE + "Crouch while in water to swim like Poseidon.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " You are a follower of " + DEITYNAME + "!");

			return toReturn;
		}
		else
		{
			toReturn.add(" "); // TODO
			toReturn.add(ChatColor.AQUA + " Demigods > " + ChatColor.RESET + DEITYCOLOR + DEITYNAME);
			toReturn.add(ChatColor.RESET + "-----------------------------------------------------");
			toReturn.add(ChatColor.YELLOW + " Active:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/reel" + ChatColor.WHITE + " - Use a fishing rod for a stronger attack.");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/drown" + ChatColor.WHITE + " - Drown your enemies in sufficating water.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " Passive:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.WHITE + "Crouch while in water to swim like Poseidon.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " Claim Items:");
			for(Material item : getClaimItems())
			{
				toReturn.add(ChatColor.GRAY + " -> " + ChatColor.WHITE + item.name());
			}
			toReturn.add(" ");

			return toReturn;
		}
	}

	// This sets the particular passive ability for the Zeus_deity deity.
	@EventHandler(priority = EventPriority.MONITOR)
	public static void onEntityDamange(EntityDamageEvent damageEvent)
	{
		if(damageEvent.getEntity() instanceof Player)
		{
			Player player = (Player) damageEvent.getEntity();
			if(!API.misc.canUseDeitySilent(player, DEITYNAME)) return;

			// If the player receives falling damage, cancel it
			if(damageEvent.getCause() == DamageCause.DROWNING)
			{
				damageEvent.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public static void onPlayerInteract(PlayerInteractEvent interactEvent)
	{
		// Set variables
		Player player = interactEvent.getPlayer();
		PlayerCharacter character = API.player.getCurrentChar(player);

		if(!API.ability.isClick(interactEvent)) return;

		if(!API.misc.canUseDeitySilent(player, DEITYNAME)) return;

		if(character.isEnabledAbility(REEL_NAME) && (player.getItemInHand().getType() == Material.FISHING_ROD))
		{
			if(!API.character.isCooledDown(player, REEL_NAME, REEL_TIME, false)) return;

			reel(player);
		}

		if(character.isEnabledAbility(DROWN_NAME) || ((player.getItemInHand() != null) && (player.getItemInHand().getType() == character.getBind(DROWN_NAME))))
		{
			if(!API.character.isCooledDown(player, DROWN_NAME, DROWN_TIME, false)) return;

			drown(player);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		if(!API.misc.canUseDeitySilent(player, DEITYNAME)) return;

		// PHELPS SWIMMING
		if(player.getLocation().getBlock().getType().equals(Material.STATIONARY_WATER) || player.getLocation().getBlock().getType().equals(Material.WATER))
		{
			Vector direction = player.getLocation().getDirection().normalize().multiply(1.3D);
			Vector victor = new Vector(direction.getX(), direction.getY(), direction.getZ());
			if(player.isSneaking())
			{
				player.setVelocity(victor);
			}
		}
	}

	/*
	 * ------------------
	 * Command Handlers
	 * ------------------
	 * 
	 * Command: "/reel"
	 */
	public static void reelCommand(Player player, String[] args)
	{
		PlayerCharacter character = API.player.getCurrentChar(player);

		if(!API.misc.hasPermissionOrOP(player, "demigods." + DEITYALLIANCE + "." + DEITYNAME)) return;

		if(!API.misc.canUseDeity(player, DEITYNAME)) return;

		if(character.isEnabledAbility(REEL_NAME))
		{
			character.toggleAbility(REEL_NAME, false);
			player.sendMessage(ChatColor.YELLOW + REEL_NAME + " is no longer active.");
		}
		else
		{
			character.toggleAbility(REEL_NAME, true);
			player.sendMessage(ChatColor.YELLOW + REEL_NAME + " is now active.");
		}
	}

	// The actual ability command
	public static void reel(Player player)
	{
		// Set variables
		PlayerCharacter character = API.player.getCurrentChar(player);
		int damage = (int) Math.ceil(0.37286 * Math.pow(character.getPower(AbilityType.OFFENSE), 0.371238));
		LivingEntity target = API.ability.autoTarget(player);

		if(!API.ability.doAbilityPreProcess(player, target, "reel", REEL_COST, AbilityType.OFFENSE)) return;
		character.subtractFavor(REEL_COST);
		REEL_TIME = System.currentTimeMillis() + REEL_DELAY;

		if(!API.ability.targeting(player, target)) return;

		API.misc.customDamage(player, target, damage, DamageCause.CUSTOM);

		if(target.getLocation().getBlock().getType() == Material.AIR)
		{
			target.getLocation().getBlock().setType(Material.WATER);
			target.getLocation().getBlock().setData((byte) 0x8);
		}
	}

	/*
	 * Command: "/drown"
	 */
	public static void drownCommand(Player player, String[] args)
	{
		PlayerCharacter character = API.player.getCurrentChar(player);

		if(!API.misc.hasPermissionOrOP(player, "demigods." + DEITYALLIANCE + "." + DEITYNAME)) return;

		if(!API.misc.canUseDeity(player, DEITYNAME)) return;

		if(args.length == 2 && args[1].equalsIgnoreCase("bind"))
		{
			// Bind item
			character.setBound(DROWN_NAME, player.getItemInHand().getType());
		}
		else
		{
			if(character.isEnabledAbility(DROWN_NAME))
			{
				character.toggleAbility(DROWN_NAME, false);
				player.sendMessage(ChatColor.YELLOW + DROWN_NAME + " is no longer active.");
			}
			else
			{
				character.toggleAbility(DROWN_NAME, true);
				player.sendMessage(ChatColor.YELLOW + DROWN_NAME + " is now active.");
			}
		}
	}

	// The actual ability command
	public static void drown(Player player)
	{
		// Define variables
		PlayerCharacter character = API.player.getCurrentChar(player);
		int power = character.getPower(AbilityType.OFFENSE);
		int radius = (int) Math.ceil(1.6955424 * Math.pow(power, 0.129349));
		int duration = (int) Math.ceil(2.80488 * Math.pow(power, 0.2689)); // seconds
		LivingEntity target = API.ability.autoTarget(player);
		if(target == null) return; // Null check
		Location toHit = API.ability.aimLocation(character, target.getLocation());

		if(!API.ability.doAbilityPreProcess(player, target, "drown", DROWN_COST, AbilityType.OFFENSE)) return;
		character.subtractFavor(DROWN_COST);

		// Set the ability's delay
		DROWN_TIME = System.currentTimeMillis() + DROWN_DELAY;

		final ArrayList<Block> toReset = new ArrayList<Block>();
		for(int x = -radius; x <= radius; x++)
		{
			for(int y = -radius; y <= radius; y++)
			{
				for(int z = -radius; z <= radius; z++)
				{
					Block block = toHit.getWorld().getBlockAt(toHit.getBlockX() + x, toHit.getBlockY() + y, toHit.getBlockZ() + z);
					if(block.getLocation().distance(toHit) <= radius)
					{
						if(block.getType() == Material.AIR)
						{
							block.setType(Material.WATER);
							block.setData((byte) (0x8));
							toReset.add(block);
						}
					}
				}
			}
		}

		if(!API.ability.isHit(target, toHit))
		{
			player.sendMessage(ChatColor.RED + "Missed...");
		}

		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(API, new Runnable()
		{
			@Override
			public void run()
			{
				for(Block block : toReset)
				{
					if((block.getType() == Material.WATER) || (block.getType() == Material.STATIONARY_WATER)) block.setType(Material.AIR);
				}
			}
		}, duration);
	}

	// Don't touch these, they're required to work.
	public String loadDeity()
	{
		API.getServer().getPluginManager().registerEvents(this, API);
		REEL_TIME = System.currentTimeMillis();
		DROWN_TIME = System.currentTimeMillis();
		return DEITYNAME + " loaded.";
	}

	public static ArrayList<String> getCommands()
	{
		ArrayList<String> COMMANDS = new ArrayList<String>();

		// List all commands
		COMMANDS.add("reel");
		COMMANDS.add("drown");

		return COMMANDS;
	}

	public static String getName()
	{
		return DEITYNAME;
	}

	public static String getAlliance()
	{
		return DEITYALLIANCE;
	}

	public static ChatColor getColor()
	{
		return DEITYCOLOR;
	}
}
