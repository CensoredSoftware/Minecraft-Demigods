package com.censoredsoftware.Demigods.Engine.Tracked;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import redis.clients.johm.*;

import com.censoredsoftware.Demigods.Engine.DemigodsData;
import com.censoredsoftware.Demigods.Engine.PlayerCharacter.PlayerCharacter;

@Model
public class TrackedPlayer
{
	@Id
	private Long id;
	@Attribute
	@Indexed
	private String player;
	@Attribute
	private long lastLoginTime;
	@Reference
	private PlayerCharacter current;
	@Reference
	private PlayerCharacter previous;

	void setPlayer(String player)
	{
		this.player = player;
	}

	public static void save(TrackedPlayer meta)
	{
		DemigodsData.jOhm.save(meta);
	}

	public static TrackedPlayer load(long id) // TODO This belongs somewhere else.
	{
		return DemigodsData.jOhm.get(TrackedPlayer.class, id);
	}

	public static Set<TrackedPlayer> loadAll()
	{
		return DemigodsData.jOhm.getAll(TrackedPlayer.class);
	}

	public static TrackedPlayer getTracked(OfflinePlayer player)
	{
		final Set<TrackedPlayer> tracking = loadAll();
		for(TrackedPlayer tracked : tracking)
		{
			if(player.getName().equals(tracked.getPlayer())) return tracked;
		}
		return TrackedModelFactory.createTrackedPlayer(player);
	}

	public OfflinePlayer getPlayer()
	{
		return Bukkit.getOfflinePlayer(this.player);
	}

	public void setLastLoginTime(long time)
	{
		this.lastLoginTime = time;
		TrackedPlayer.save(this);
	}

	public Long getLastLoginTime()
	{
		return this.lastLoginTime;
	}

	public void setCurrent(PlayerCharacter character)
	{
		this.previous = this.current;
		this.current = character;
		TrackedPlayer.save(this);
	}

	public PlayerCharacter getCurrent()
	{
		return this.current;
	}

	public PlayerCharacter getPrevious()
	{
		return this.previous;
	}
}
