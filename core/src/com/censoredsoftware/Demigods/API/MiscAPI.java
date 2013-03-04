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

package com.censoredsoftware.Demigods.API;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.censoredsoftware.Demigods.Demigods;
import com.censoredsoftware.Demigods.Libraries.Objects.PlayerCharacter;

public class MiscAPI
{
	// Define variables
	private static final Demigods API = Demigods.INSTANCE;
	private final String plugin_name = "Demigods";
	private final Logger log = Logger.getLogger("Minecraft");

	/*
	 * getLog() : Returns an instance of the Logger.
	 */
	public Logger getLog()
	{
		return log;
	}

	/*
	 * callEvent() : Calls an event.
	 */
	public void callEvent(Event event)
	{
		API.getServer().getPluginManager().callEvent(event);
	}

	/*
	 * customDamage() : Creates custom damage for (LivingEntity)target from (LivingEntity)source with ammount (int)amount.
	 */
	public void customDamage(LivingEntity source, LivingEntity target, int amount, DamageCause cause)
	{
		if(target instanceof Player)
		{
			if(source instanceof Player)
			{
				target.setLastDamageCause(new EntityDamageByEntityEvent(source, target, cause, amount));
			}
			else target.damage(amount);
		}
		else target.damage(amount);
	}

	/*
	 * taggedMessage() : Sends tagged message (String)msg to the (CommandSender)sender.
	 */
	public void taggedMessage(CommandSender sender, String title)
	{
		sender.sendMessage(" ");
		sender.sendMessage(ChatColor.AQUA + " " + plugin_name + " > " + ChatColor.RESET + title);
		sender.sendMessage(ChatColor.RESET + "-----------------------------------------------------");
	}

	/*
	 * info() : Sends console message with "info" tag.
	 */
	public void info(String msg)
	{
		log.info("[" + plugin_name + "] " + msg);
	}

	/*
	 * warning() : Sends console message with "warning" tag.
	 */
	public void warning(String msg)
	{
		log.warning("[" + plugin_name + "] " + msg);
	}

	/*
	 * severe() : Sends console message with "severe" tag.
	 */
	public void severe(String msg)
	{
		log.severe("[" + plugin_name + "] " + msg);
	}

	/*
	 * serverMsg() : Send (String)msg to the server chat.
	 */
	public void serverMsg(String msg)
	{
		API.getServer().broadcastMessage(msg);
	}

	/*
	 * hasPermission() : Checks if (Player)player has permission (String)permission.
	 */
	public boolean hasPermission(Player player, String permission)
	{
		return player == null || player.hasPermission(permission);
	}

	/*
	 * hasPermissionOrOP() : Checks if (Player)player has permission (String)permission, or is OP.
	 */
	public boolean hasPermissionOrOP(Player player, String permission)
	{
		return player == null || player.isOp() || player.hasPermission(permission);
	}

	/*
	 * noPermission() : Command sender does not have permission to run command.
	 */
	public boolean noPermission(Player player)
	{
		player.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
		return true;
	}

	/*
	 * noConsole() : Sends a permission denial message to the console.
	 */
	public boolean noConsole(CommandSender sender)
	{
		sender.sendMessage("This command can only be executed by a player.");
		return true;
	}

	/*
	 * noPlayer() : Sends a permission denial message to the console.
	 */
	public boolean noPlayer(CommandSender sender)
	{
		sender.sendMessage("This command can only be executed by the console.");
		return true;
	}

	/*
	 * canUseDeity() : Checks is a player can use a specfic deity and returns a message
	 */
	public boolean canUseDeity(Player player, String deity)
	{
		PlayerCharacter character = API.player.getCurrentChar(player);

		// Check the player for DEITYNAME
		if(character != null && !character.hasDeity(deity))
		{
			player.sendMessage(ChatColor.RED + "You haven't claimed " + deity + "! You can't do that!");
			return false;
		}
		else if(character == null || !character.isImmortal())
		{
			player.sendMessage(ChatColor.RED + "You can't do that, mortal!");
			return false;
		}
		return true;
	}

	/*
	 * canUseDeitySilent() : Checks is a player can use a specfic deity without returning a message.
	 */
	public boolean canUseDeitySilent(Player player, String deity)
	{
		PlayerCharacter character = API.player.getCurrentChar(player);

		if(character == null) return false;

		// Check the player for DEITYNAME
		return character.hasDeity(deity) && character.isImmortal();
	}
}
