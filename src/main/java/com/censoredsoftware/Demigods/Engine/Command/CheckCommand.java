package com.censoredsoftware.Demigods.Engine.Command;

import com.censoredsoftware.Demigods.Engine.Demigods;
import com.censoredsoftware.Demigods.Engine.Object.General.DemigodsPlayer;
import com.censoredsoftware.Demigods.Engine.Object.PlayerCharacter.PlayerCharacter;
import com.censoredsoftware.Demigods.Engine.Utility.MiscUtility;
import com.censoredsoftware.Demigods.Engine.Utility.UnicodeUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckCommand implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = Bukkit.getOfflinePlayer(sender.getName()).getPlayer();
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();

		if(character == null || !character.isImmortal())
		{
			player.sendMessage(ChatColor.RED + "You cannot use that command, mortal.");
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
		ChatColor favorColor = MiscUtility.getColor(character.getMeta().getFavor(), character.getMeta().getMaxFavor());

		// Send the user their info via chat
		Demigods.message.tagged(sender, "Player Check");

		sender.sendMessage(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.RESET + "Character: " + ChatColor.AQUA + charName);
		sender.sendMessage(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.RESET + "Deity: " + deityColor + deity + ChatColor.WHITE + " of the " + ChatColor.GOLD + MiscUtility.capitalize(alliance) + "s");
		sender.sendMessage(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.RESET + "Favor: " + favorColor + favor + ChatColor.GRAY + " (of " + ChatColor.GREEN + maxFavor + ChatColor.GRAY + ")");
		sender.sendMessage(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.RESET + "Ascensions: " + ChatColor.GREEN + ascensions);
		sender.sendMessage(ChatColor.GRAY + " " + UnicodeUtility.rightwardArrow() + " " + ChatColor.RESET + "Kills: " + ChatColor.GREEN + kills + ChatColor.WHITE + " / Deaths: " + ChatColor.RED + deaths + ChatColor.WHITE);

		return true;
	}
}
