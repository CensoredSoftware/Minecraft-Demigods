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

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import com.censoredsoftware.Demigods.Demigods;
import com.censoredsoftware.Demigods.Event.Ability.AbilityEvent.AbilityType;
import com.censoredsoftware.Demigods.Objects.Character.PlayerCharacter;
import com.censoredsoftware.Demigods.Theogony.Theogony;

public class Zeus_deity implements Listener
{
	private static final Demigods API = Theogony.INSTANCE;

	// Create required universal deity variables
	private static final String DEITYNAME = "Zeus";
	private static final String DEITYALLIANCE = "God";
	private static final ChatColor DEITYCOLOR = ChatColor.YELLOW;

	/*
	 * Set deity-specific ability variable(s).
	 */
	// "/shove" Command:
	private static final String SHOVE_NAME = "Shove"; // Sets the name of this command
	private static long SHOVE_TIME; // Creates the variable for later use
	private static final int SHOVE_COST = 170; // Cost to run command in "favor"
	private static final int SHOVE_DELAY = 1500; // In milliseconds

	// "/lightning" Command:
	private static final String LIGHTNING_NAME = "Lightning"; // Sets the name of this command
	private static long LIGHTNING_TIME; // Creates the variable for later use
	private static final int LIGHTNING_COST = 140; // Cost to run command in "favor"
	private static final int LIGHTNING_DELAY = 1000; // In milliseconds

	// "/storm" Command:
	@SuppressWarnings("unused")
	private static String ULTIMATE_NAME = "Storm";
	private static long ULTIMATE_TIME; // Creates the variable for later use
	private static final int ULTIMATE_COST = 3700; // Cost to run command in "favor"
	private static final int ULTIMATE_COOLDOWN_MAX = 600; // In seconds
	private static final int ULTIMATE_COOLDOWN_MIN = 60; // In seconds

	public ArrayList<Material> getClaimItems()
	{
		ArrayList<Material> claimItems = new ArrayList<Material>();

		// Add new items in this format: claimItems.add(Material.NAME_OF_MATERIAL);
		// claimItems.add(Material.IRON_INGOT);
		// claimItems.add(Material.FEATHER);
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
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/shove" + ChatColor.WHITE + " - Shove your target away from you.");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/lightning" + ChatColor.WHITE + " - Strike lightning upon your enemies.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " Passive:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.WHITE + "Take no damage from falling.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " Ultimate:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/storm" + ChatColor.WHITE + " - Throw all of your enemies into the sky as lightning fills the heavens.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " You are a follower of " + DEITYNAME + "!");
			toReturn.add(" ");

			return toReturn;
		}
		else
		{
			toReturn.add(" "); // TODO
			toReturn.add(ChatColor.AQUA + " Demigods > " + ChatColor.RESET + DEITYCOLOR + DEITYNAME);
			toReturn.add(ChatColor.RESET + "-----------------------------------------------------");
			toReturn.add(ChatColor.YELLOW + " Active:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/shove" + ChatColor.WHITE + " - Shove your target away from you.");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/lightning" + ChatColor.WHITE + " - Strike lightning upon your enemies.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " Passive:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.WHITE + "Take no damage from falling.");
			toReturn.add(" ");
			toReturn.add(ChatColor.YELLOW + " Ultimate:");
			toReturn.add(ChatColor.GRAY + " -> " + ChatColor.GREEN + "/storm" + ChatColor.WHITE + " - Throw all of your enemies into the sky as lightning fills the heavens.");
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
			if(damageEvent.getCause() == DamageCause.FALL)
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

		if(character.isEnabledAbility(SHOVE_NAME) || ((player.getItemInHand() != null) && (player.getItemInHand().getType() == character.getBind(SHOVE_NAME))))
		{
			if(!API.character.isCooledDown(player, SHOVE_NAME, SHOVE_TIME, false)) return;

			shove(player);
		}

		if(character.isEnabledAbility(LIGHTNING_NAME) || ((player.getItemInHand() != null) && (player.getItemInHand().getType() == character.getBind(LIGHTNING_NAME))))
		{
			if(!API.character.isCooledDown(player, LIGHTNING_NAME, LIGHTNING_TIME, false)) return;

			lightning(player);
		}
	}

	/*
	 * ------------------
	 * Command Handlers
	 * ------------------
	 * 
	 * Command: "/shove"
	 */
	public static void shoveCommand(Player player, String[] args)
	{
		PlayerCharacter character = API.player.getCurrentChar(player);

		if(!API.misc.hasPermissionOrOP(player, "demigods." + DEITYALLIANCE + "." + DEITYNAME)) return;

		if(!API.misc.canUseDeity(player, DEITYNAME)) return;

		if(args.length == 2 && args[1].equalsIgnoreCase("bind"))
		{
			// Bind item
			character.setBound(SHOVE_NAME, player.getItemInHand().getType());
		}
		else
		{
			if(character.isEnabledAbility(SHOVE_NAME))
			{
				character.toggleAbility(SHOVE_NAME, false);
				player.sendMessage(ChatColor.YELLOW + SHOVE_NAME + " is no longer active.");
			}
			else
			{
				character.toggleAbility(SHOVE_NAME, true);
				player.sendMessage(ChatColor.YELLOW + SHOVE_NAME + " is now active.");
			}
		}
	}

	// The actual ability command
	public static void shove(Player player)
	{
		// Define variables
		PlayerCharacter character = API.player.getCurrentChar(player);
		int devotion = character.getDevotion();
		double multiply = 0.1753 * Math.pow(devotion, 0.322917);
		LivingEntity target = API.ability.autoTarget(player);

		if(!API.ability.doAbilityPreProcess(player, target, "shove", SHOVE_COST, AbilityType.PASSIVE)) return;
		SHOVE_TIME = System.currentTimeMillis() + SHOVE_DELAY;
		character.subtractFavor(SHOVE_COST);

		if(!API.ability.targeting(player, target)) return;

		Vector vector = player.getLocation().toVector();
		Vector victor = target.getLocation().toVector().subtract(vector);
		victor.multiply(multiply);
		target.setVelocity(victor);
	}

	/*
	 * Command: "/lightning"
	 */
	public static void lightningCommand(Player player, String[] args)
	{
		PlayerCharacter character = API.player.getCurrentChar(player);

		if(!API.misc.hasPermissionOrOP(player, "demigods." + DEITYALLIANCE + "." + DEITYNAME)) return;

		if(!API.misc.canUseDeity(player, DEITYNAME)) return;

		if(args.length == 2 && args[1].equalsIgnoreCase("bind"))
		{
			// Bind item
			character.setBound(LIGHTNING_NAME, player.getItemInHand().getType());
		}
		else
		{
			if(character.isEnabledAbility(LIGHTNING_NAME))
			{
				character.toggleAbility(LIGHTNING_NAME, false);
				player.sendMessage(ChatColor.YELLOW + LIGHTNING_NAME + " is no longer active.");
			}
			else
			{
				character.toggleAbility(LIGHTNING_NAME, true);
				player.sendMessage(ChatColor.YELLOW + LIGHTNING_NAME + " is now active.");
			}
		}
	}

	// The actual ability command
	public static void lightning(Player player)
	{
		// Define variables
		PlayerCharacter character = API.player.getCurrentChar(player);
		LivingEntity target = API.ability.autoTarget(player);

		if(!API.ability.doAbilityPreProcess(player, target, "lightning", LIGHTNING_COST, AbilityType.OFFENSE)) return;
		LIGHTNING_TIME = System.currentTimeMillis() + LIGHTNING_DELAY;
		character.subtractFavor(LIGHTNING_COST);

		strikeLightning(player, target);
	}

	/*
	 * Command: "/storm"
	 */
	public static void stormCommand(Player player, String[] args)
	{
		if(!API.misc.hasPermissionOrOP(player, "demigods." + DEITYALLIANCE + "." + DEITYNAME + ".ultimate")) return;

		// Set variables
		PlayerCharacter character = API.player.getCurrentChar(player);

		// Check the player for DEITYNAME
		if(!character.hasDeity(DEITYNAME)) return;

		// Check if the ultimate has cooled down or not
		if(System.currentTimeMillis() < ULTIMATE_TIME)
		{
			player.sendMessage(ChatColor.YELLOW + "You cannot use the " + DEITYNAME + " ultimate again for " + ChatColor.WHITE + ((((ULTIMATE_TIME) / 1000) - (System.currentTimeMillis() / 1000))) / 60 + " minutes");
			player.sendMessage(ChatColor.YELLOW + "and " + ChatColor.WHITE + ((((ULTIMATE_TIME) / 1000) - (System.currentTimeMillis() / 1000)) % 60) + " seconds.");
			return;
		}

		if(!API.ability.doAbilityPreProcess(player, "storm", ULTIMATE_COST, AbilityType.OFFENSE)) return;

		// Perform ultimate if there is enough favor
		int count = storm(player);
		if(count == 0)
		{
			player.sendMessage(ChatColor.YELLOW + "Zeus unable to strike any targets.");
			return;
		}

		player.sendMessage(ChatColor.YELLOW + "Zeus has struck " + count + " targets!");

		// Set favor and cooldown
		character.subtractFavor(ULTIMATE_COST);
		player.setNoDamageTicks(1000);
		int cooldownMultiplier = (int) (ULTIMATE_COOLDOWN_MAX - ((ULTIMATE_COOLDOWN_MAX - ULTIMATE_COOLDOWN_MIN) * ((double) character.getAscensions() / 100)));
		ULTIMATE_TIME = System.currentTimeMillis() + cooldownMultiplier * 1000;
	}

	// The actual ability command
	public static int storm(Player player)
	{
		// Define variables
		ArrayList<Entity> entityList = new ArrayList<Entity>();
		Vector playerLocation = player.getLocation().toVector();

		for(Entity anEntity : player.getWorld().getEntities())
			if(anEntity.getLocation().toVector().isInSphere(playerLocation, 50.0)) entityList.add(anEntity);

		int count = 0;
		for(Entity entity : entityList)
		{
			try
			{
				if(entity instanceof Player)
				{
					Player otherPlayer = (Player) entity;
					if(!API.player.areAllied(player, otherPlayer) && !otherPlayer.equals(player))
					{
						if(strikeLightning(player, otherPlayer)) count++;
						strikeLightning(player, otherPlayer);
						strikeLightning(player, otherPlayer);
					}
				}
				else if(entity instanceof LivingEntity)
				{
					LivingEntity livingEntity = (LivingEntity) entity;
					if(strikeLightning(player, livingEntity)) count++;
					strikeLightning(player, livingEntity);
					strikeLightning(player, livingEntity);
				}
			}
			catch(Exception ignored)
			{}
		}

		return count;
	}

	private static boolean strikeLightning(Player player, LivingEntity target)
	{
		// Set variables
		PlayerCharacter character = API.player.getCurrentChar(player);

		if(!player.getWorld().equals(target.getWorld())) return false;
		if(!API.zone.canTarget(target)) return false;
		Location toHit = API.ability.aimLocation(character, target.getLocation());

		player.getWorld().strikeLightningEffect(toHit);

		for(Entity entity : toHit.getBlock().getChunk().getEntities())
		{
			if(entity instanceof LivingEntity)
			{
				if(!API.zone.canTarget(entity)) continue;
				LivingEntity livingEntity = (LivingEntity) entity;
				if(livingEntity.getLocation().distance(toHit) < 1.5) API.misc.customDamage(player, livingEntity, character.getAscensions() * 2, DamageCause.LIGHTNING);
			}
		}

		if(!API.ability.isHit(target, toHit))
		{
			player.sendMessage(ChatColor.RED + "Missed...");
		}

		return true;
	}

	// Don't touch these, they're required to work.
	public String loadDeity()
	{
		API.getServer().getPluginManager().registerEvents(this, API);
		ULTIMATE_TIME = System.currentTimeMillis();
		SHOVE_TIME = System.currentTimeMillis();
		LIGHTNING_TIME = System.currentTimeMillis();
		return DEITYNAME + " loaded.";
	}

	public static ArrayList<String> getCommands()
	{
		ArrayList<String> COMMANDS = new ArrayList<String>();

		// List all commands
		COMMANDS.add("shove");
		COMMANDS.add("lightning");
		COMMANDS.add("storm");

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
