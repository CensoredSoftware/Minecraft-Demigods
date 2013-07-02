package com.censoredsoftware.Demigods.Engine.Listener;

import com.censoredsoftware.Demigods.Engine.Object.General.DemigodsPlayer;
import com.censoredsoftware.Demigods.Engine.Object.PlayerCharacter.PlayerCharacter;
import com.censoredsoftware.Demigods.Engine.Utility.DataUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ChatListener implements Listener
{
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChatCommand(AsyncPlayerChatEvent event)
	{
		// Define variables
		Player player = event.getPlayer();
		Set<Player> viewing = event.getRecipients();
		String message = event.getMessage();

		if(message.equals("pl")) pl(player, event);

		// No chat toggle
		if(DataUtility.hasKeyTemp(player.getName(), "temp_no_chat")) event.setCancelled(true);
		for(Player victim : Bukkit.getOnlinePlayers())
		{
			if(DataUtility.hasKeyTemp(victim.getName(), "temp_no_chat")) viewing.remove(victim);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChatMessage(AsyncPlayerChatEvent event)
	{
		// Define variables
		Player player = event.getPlayer();
		String message = event.getMessage();

		// Return if the player is praying
		if(DemigodsPlayer.isPraying(player)) return;

		// Handle chat for character switching
		if(DataUtility.hasKeyTemp(player.getName(), "temp_chat_number"))
		{
			// Define variables
			PlayerCharacter prevChar = DemigodsPlayer.getPlayer(player).getPrevious();

			if(prevChar == null) return;

			DataUtility.saveTemp(player.getName(), "temp_chat_number", Integer.parseInt(DataUtility.getValueTemp(player.getName(), "temp_chat_number").toString()) + 1);
			if(DataUtility.hasKeyTemp(player.getName(), "temp_chat_number") && Integer.parseInt(DataUtility.getValueTemp(player.getName(), "temp_chat_number").toString()) <= 2) event.setMessage(ChatColor.GRAY + "(Previously " + prevChar.getDeity().getInfo().getColor() + prevChar.getName() + ChatColor.GRAY + ") " + ChatColor.WHITE + message);
			else DataUtility.removeTemp(player.getName(), "temp_chat_number");
		}
	}

	private void pl(Player player, AsyncPlayerChatEvent event)
	{
		HashMap<String, ArrayList<String>> alliances = new HashMap<String, ArrayList<String>>();

		for(Player onlinePlayer : Bukkit.getOnlinePlayers())
		{
			String alliance = DemigodsPlayer.getCurrentAlliance(player);

			if(!alliances.containsKey(alliance.toUpperCase())) alliances.put(alliance.toUpperCase(), new ArrayList<String>());
			alliances.get(alliance.toUpperCase()).add(onlinePlayer.getName());
		}

		for(String alliance : alliances.keySet())
		{
			String names = "";
			for(String name : alliances.get(alliance))
			{
				names += " " + name;
			}
			player.sendMessage(ChatColor.YELLOW + alliance + ": " + ChatColor.WHITE + names);
		}

		event.getRecipients().clear();
		event.setCancelled(true);
	}
}
