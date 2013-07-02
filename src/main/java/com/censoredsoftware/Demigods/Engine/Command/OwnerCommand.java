package com.censoredsoftware.Demigods.Engine.Command;

import com.censoredsoftware.Demigods.Engine.Demigods;
import com.censoredsoftware.Demigods.Engine.Object.PlayerCharacter.PlayerCharacter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OwnerCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
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
		PlayerCharacter charToCheck = PlayerCharacter.getCharacterByName(args[0]);
		if(charToCheck.getName() == null) player.sendMessage(ChatColor.RED + "That character doesn't exist.");
		else player.sendMessage(charToCheck.getDeity().getInfo().getColor() + charToCheck.getName() + ChatColor.YELLOW + " belongs to " + charToCheck.getOfflinePlayer().getName() + ".");
		return true;
	}
}
