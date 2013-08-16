package com.censoredsoftware.demigods.conversation;

import com.censoredsoftware.demigods.Demigods;
import com.censoredsoftware.demigods.util.Times;
import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChatRecorder
{
	private Map<Long, String> lines; // Format: <System.currentTimeMillis, Message>
	private Player player;
	private Listener listener;

	public void start(Player player)
	{
		this.player = player;
		this.listener = new ChatListener();
		this.lines = Maps.newTreeMap();
	}

	public List<String> stop()
	{
		HandlerList.unregisterAll(this.listener);

		return new ArrayList<String>(lines.size())
		{
			{
				for(Map.Entry<Long, String> entry : lines.entrySet())
				{
					String time = Times.getTimeTagged(entry.getKey(), true);
					add(ChatColor.GRAY + "[" + time + " ago]" + entry.getValue());
				}
			}
		};
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

	class ChatListener implements Listener
	{
		@EventHandler(priority = EventPriority.MONITOR)
		private void onChatEvent(AsyncPlayerChatEvent event)
		{
			if(event.getRecipients().contains(player)) lines.put(System.currentTimeMillis(), event.getFormat());
		}
	}
}
