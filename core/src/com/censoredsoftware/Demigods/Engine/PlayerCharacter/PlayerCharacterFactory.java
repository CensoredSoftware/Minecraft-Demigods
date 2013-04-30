package com.censoredsoftware.Demigods.Engine.PlayerCharacter;

import java.util.Map;

import org.bukkit.OfflinePlayer;

/**
 * Factory for creating Demigod objects.
 */
public class PlayerCharacterFactory
{
	private int globalMaxFavor;

	/**
	 * Constructor for the factory.
	 * 
	 * @param globalMaxFavor The max favor setting.
	 */
	public PlayerCharacterFactory(int globalMaxFavor)
	{
		this.globalMaxFavor = globalMaxFavor;
	}

	/**
	 * Create a PlayerCharacter from scratch.
	 * 
	 * @param player The player holding this class.
	 * @param charID The ID of the character.
	 * @param charName The name of the character.
	 * @param charActive True if the character is active.
	 * @param className The name of the class.
	 * @param teamName The team this character class belongs to.
	 * @param favor The favor points.
	 * @param maxFavor The max favor.
	 * @param devotion The devotion points.
	 * @param ascensions The ascension points.
	 * @param offense The offense level.
	 * @param defense The defense level.
	 * @param stealth The stealth level.
	 * @param support The support level.
	 * @param passive The passive level.
	 * @param classActive True if the class is active.
	 * @return The Demigod object.
	 */
	public PlayerCharacter create(OfflinePlayer player, int charID, String charName, boolean charActive, String className, String teamName, int favor, int maxFavor, int devotion, int ascensions, int offense, int defense, int stealth, int support, int passive, boolean classActive)
	{
		return new PlayerCharacter(globalMaxFavor, player, charID, charName, charActive, className, teamName, favor, maxFavor, devotion, ascensions, offense, defense, stealth, support, passive, classActive);
	}

	public PlayerCharacter create(Map map)
	{
		return new PlayerCharacter(globalMaxFavor, map);
	}
}