package com.censoredsoftware.Demigods.Engine.Event.Character;

import com.censoredsoftware.Demigods.Engine.Object.PlayerCharacter.PlayerCharacter;
import org.bukkit.event.HandlerList;

/*
 * Represents an callAbilityEvent that is called when a player is killed by another player.
 */
public class CharacterBetrayCharacterEvent extends CharacterKillCharacterEvent
{
	private static final HandlerList handlers = new HandlerList();
	private PlayerCharacter killedChar;
	private String alliance;

	public CharacterBetrayCharacterEvent(final PlayerCharacter attacker, final PlayerCharacter killedChar, final String alliance)
	{
		super(attacker, killedChar);
		this.alliance = alliance;
	}

	/*
	 * getAlliance() : Gets the alliance involved.
	 */
	public String getAlliance()
	{
		return this.alliance;
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
}
