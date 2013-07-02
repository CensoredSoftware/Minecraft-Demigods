package com.censoredsoftware.Demigods.Engine.Object.Language;

import java.util.Set;

public interface Blacklist
{
	/**
	 * The version of this language text.
	 * 
	 * @return Integer version.
	 */
	public int version();

	/**
	 * The translator to credit.
	 * 
	 * @return Name of translator.
	 */
	public String translator();

	/**
	 * Get the blacklist.
	 * 
	 * @return The Set of Strings.
	 */
	public Set<String> getBlackList();
}
