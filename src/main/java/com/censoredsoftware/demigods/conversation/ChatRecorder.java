package com.censoredsoftware.demigods.conversation;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.censoredsoftware.demigods.Demigods;
import com.google.common.collect.Lists;

public class ChatRecorder
{
	private ArrayList<String> lines;
	private Player player;
	private Listener listener;

	public void start(Player player)
	{
		this.player = player;
		this.listener = new ChatListener();
		this.lines = Lists.newArrayList();
	}

	public ArrayList<String> stop()
	{
		HandlerList.unregisterAll(this.listener);
		return lines;
	}

	public Listener getListener()
	{
		return this.listener;
	}

	public static class Util
	{
		public static ChatRecorder startRecording(Player player)
		{
			ChatRecorder recorder = new ChatRecorder();
			recorder.start(player);
			Demigods.plugin.getServer().getPluginManager().registerEvents(recorder.getListener(), Demigods.plugin);
			return recorder;
		}
	}

	class ChatListener implements org.bukkit.event.Listener
	{
		@EventHandler(priority = EventPriority.MONITOR)
		private void onChatEvent(AsyncPlayerChatEvent event)
		{
			if(event.getRecipients().contains(player)) lines.add(event.getFormat());
		}
	}
}