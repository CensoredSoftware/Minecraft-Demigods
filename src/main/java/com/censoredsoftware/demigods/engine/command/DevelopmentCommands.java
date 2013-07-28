package com.censoredsoftware.demigods.engine.command;

import java.util.Set;

import com.censoredsoftware.demigods.engine.util.StopWatches;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import com.censoredsoftware.demigods.engine.battle.Battle;
import com.censoredsoftware.demigods.engine.location.Region;
import com.censoredsoftware.demigods.engine.player.DCharacter;
import com.censoredsoftware.demigods.engine.player.DPlayer;
import com.google.common.collect.Sets;

public class DevelopmentCommands extends DCommand
{
	@Override
	public Set<String> getCommands()
	{
		return Sets.newHashSet("test1", "test2", "test3", "hspawn", "soundtest", "removechar");
	}

	@Override
	public boolean process(CommandSender sender, Command command, String[] args)
	{
		if(command.getName().equalsIgnoreCase("removechar")) return removeChar(sender, args);
		else if(command.getName().equalsIgnoreCase("test1")) return test1(sender, args);
		else if(command.getName().equalsIgnoreCase("test2")) return test2(sender, args);
		else if(command.getName().equalsIgnoreCase("test3")) return test3(sender, args);
		else if(command.getName().equalsIgnoreCase("hspawn")) return hspawn(sender);
		else if(command.getName().equalsIgnoreCase("soundtest")) return soundTest(sender, args);
		return false;
	}

	private static boolean test1(CommandSender sender, final String[] args)
	{
		Player player = (Player) sender;

		player.sendMessage("Disabling all battles...");

		for(Battle battle : Battle.Util.getAllActive())
		{
			battle.end();
		}

		player.sendMessage("All battles disabled!");

		return true;
	}

	private static boolean test2(CommandSender sender, final String[] args)
	{
		Player player = (Player) sender;

		Region region = DPlayer.Util.getPlayer(player).getRegion();

		player.sendMessage(ChatColor.YELLOW + "X: " + region.getX());
		player.sendMessage(ChatColor.YELLOW + "Z: " + region.getZ());

		return true;
	}

	private static boolean test3(CommandSender sender, final String[] args)
	{
		Player player = (Player) sender;

        StopWatches.end(StopWatches.start(), "test3");

		return true;
	}

	private static boolean hspawn(CommandSender sender)
	{
		Player player = (Player) sender;

		// This SHOULD happen automatically, but bukkit doesn't do this, so we need to.

		if(player.isInsideVehicle() && player.getVehicle() instanceof Horse)
		{
			Horse horse = (Horse) player.getVehicle();
			horse.eject();
			horse.teleport(player.getWorld().getSpawnLocation());
			horse.setPassenger(player);
			player.sendMessage(ChatColor.YELLOW + "Teleported to spawn...");
		}

		return true;
	}

	private static boolean soundTest(CommandSender sender, final String[] args)
	{
		if(sender instanceof ConsoleCommandSender) return false;
		Player player = (Player) sender;
		try
		{
			Sound sound = Sound.valueOf(args[0].toUpperCase());
			if(!isFloat(args[1].toUpperCase()))
			{
				player.sendMessage(ChatColor.RED + "Set a pitch, ie: 1F");
				return false;
			}
			else
			{
				player.playSound(player.getLocation(), sound, 1F, Float.parseFloat(args[1].toUpperCase()));
				player.sendMessage(ChatColor.YELLOW + "Sound played.");
				return true;
			}
		}
		catch(Exception ignored)
		{}
		player.sendMessage(ChatColor.RED + "Wrong arguments, please try again.");
		return false;
	}

	/**
	 * Check to see if an input string is a float.
	 * 
	 * @param string The input string.
	 * @return True if the string is a float.
	 */
	private static boolean isFloat(String string)
	{
		try
		{
			Float.parseFloat(string);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	private static boolean removeChar(CommandSender sender, String[] args)
	{
		if(args.length != 1) return false;

		// Define args
		Player player = Bukkit.getOfflinePlayer(sender.getName()).getPlayer();
		String charName = args[0];

		if(DPlayer.Util.hasCharName(player, charName))
		{
			DCharacter character = DCharacter.Util.getCharacterByName(charName);
			character.remove();

			sender.sendMessage(ChatColor.RED + "Character removed!");
		}
		else sender.sendMessage(ChatColor.RED + "There was an error while removing your character.");

		return true;
	}
}
