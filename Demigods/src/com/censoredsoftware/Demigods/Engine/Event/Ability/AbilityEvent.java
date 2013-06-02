package com.censoredsoftware.Demigods.Engine.Event.Ability;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.censoredsoftware.Demigods.Engine.Ability.Ability;
import com.censoredsoftware.Demigods.Engine.PlayerCharacter.PlayerCharacter;

/*
 * Represents an event that is called when an ability is executed.
 */
public class AbilityEvent extends Event implements Cancellable
{
	private static final HandlerList handlers = new HandlerList();
	private String name;
	private PlayerCharacter character;
	private String deity;
	private int cost;
	private Ability.Type type;
	private boolean cancelled = false;

	public AbilityEvent(final String name, final PlayerCharacter character, final int cost, final Ability.Type type)
	{
		this.name = name;
		this.character = character;
		this.deity = character.getDeity().getInfo().getName();
		this.cost = cost;
		this.type = type;
	}

	/*
	 * getName() : Gets the ability's name.
	 */
	public String getName()
	{
		return this.name;
	}

	/*
	 * getCharacter() : Gets the character involved.
	 */
	public PlayerCharacter getCharacter()
	{
		return this.character;
	}

	/*
	 * getDeity() : Gets the deity involved.
	 */
	public String getDeity()
	{
		return this.deity;
	}

	/*
	 * getCost() : Gets an ability's cost.
	 */
	public int getCost()
	{
		return this.cost;
	}

	/*
	 * getType() : Gets an ability's type.
	 */
	public Ability.Type getType()
	{
		return this.type;
	}

	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}

	@Override
	public boolean isCancelled()
	{
		return this.cancelled;
	}

	@Override
	public synchronized void setCancelled(boolean cancelled)
	{
		this.cancelled = cancelled;
	}
}