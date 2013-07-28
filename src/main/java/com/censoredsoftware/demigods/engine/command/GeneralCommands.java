package com.censoredsoftware.demigods.engine.command;

import com.censoredsoftware.core.improve.ListedCommand;
import com.censoredsoftware.core.util.Unicodes;
import com.censoredsoftware.demigods.engine.Demigods;
import com.censoredsoftware.demigods.engine.conversation.Prayer;
import com.censoredsoftware.demigods.engine.player.DCharacter;
import com.censoredsoftware.demigods.engine.player.DPlayer;
import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class GeneralCommands extends ListedCommand
{
	@Override
	public Set<String> getCommands()
	{
		return Sets.newHashSet("check", "owner");
	}

	@Override
	public boolean processCommand(CommandSender sender, Command command, String[] args)
	{
		if(command.getName().equalsIgnoreCase("check")) return check(sender);
		else if(command.getName().equalsIgnoreCase("owner")) return owner(sender, args);
		return false;
	}

	private boolean check(CommandSender sender)
	{
		Player player = Bukkit.getOfflinePlayer(sender.getName()).getPlayer();
		DCharacter character = DPlayer.Util.getPlayer(player).getCurrent();

		if(character == null || !character.isImmortal())
		{
			player.sendMessage(ChatColor.RED + "You are nothing but a mortal. You have no worthy statistics.");
			return true;
		}

		// Define variables
		int kills = character.getKills();
		int deaths = character.getDeaths();
		String charName = character.getName();
		String deity = character.getDeity().getInfo().getName();
		String alliance = character.getAlliance();
		int favor = character.getMeta().getFavor();
		int maxFavor = character.getMeta().getMaxFavor();
		int ascensions = character.getMeta().getAscensions();
		ChatColor deityColor = character.getDeity().getInfo().getColor();
		ChatColor favorColor = Prayer.Util.getColor(character.getMeta().getFavor(), character.getMeta().getMaxFavor());

		// Send the user their info via chat
		Demigods.message.tagged(sender, "Player Check");

		sender.sendMessage(ChatColor.GRAY + " " + Unicodes.rightwardArrow() + " " + ChatColor.RESET + "Character: " + deityColor + charName);
		sender.sendMessage(ChatColor.GRAY + " " + Unicodes.rightwardArrow() + " " + ChatColor.RESET + "Deity: " + deityColor + deity + ChatColor.WHITE + " of the " + ChatColor.GOLD + StringUtils.capitalize(alliance) + "s");
		sender.sendMessage(ChatColor.GRAY + " " + Unicodes.rightwardArrow() + " " + ChatColor.RESET + "Favor: " + favorColor + favor + ChatColor.GRAY + " (of " + ChatColor.GREEN + maxFavor + ChatColor.GRAY + ")");
		sender.sendMessage(ChatColor.GRAY + " " + Unicodes.rightwardArrow() + " " + ChatColor.RESET + "Ascensions: " + ChatColor.GREEN + ascensions);
		sender.sendMessage(ChatColor.GRAY + " " + Unicodes.rightwardArrow() + " " + ChatColor.RESET + "Kills: " + ChatColor.GREEN + kills + ChatColor.WHITE + " / Deaths: " + ChatColor.RED + deaths + ChatColor.WHITE);

		return true;
	}

	private boolean owner(CommandSender sender, String[] args)
	{
		// Check Permissions
		if(!sender.hasPermission("demigods.basic")) return Demigods.message.noPermission(sender);

		Player player = Bukkit.getOfflinePlayer(sender.getName()).getPlayer();
		if(args.length < 1)
		{
			player.sendMessage(ChatColor.RED + "You must select a character.");
			player.sendMessage(ChatColor.RED + "/owner <character>");
			return true;
		}
		DCharacter charToCheck = DCharacter.Util.getCharacterByName(args[0]);
		if(charToCheck.getName() == null) player.sendMessage(ChatColor.RED + "That character doesn't exist.");
		else player.sendMessage(charToCheck.getDeity().getInfo().getColor() + charToCheck.getName() + ChatColor.YELLOW + " belongs to " + charToCheck.getOfflinePlayer().getName() + ".");
		return true;
	}
}
