/*
	Copyright (c) 2013 The Demigods Team
	
	Demigods License v1
	
	This plugin is provided "as is" and without any warranty.  Any express or
	implied warranties, including, but not limited to, the implied warranties
	of merchantability and fitness for a particular purpose are disclaimed.
	In no event shall the authors be liable to any party for any direct,
	indirect, incidental, special, exemplary, or consequential damages arising
	in any way out of the use or misuse of this plugin.
	
	Definitions
	
	 1. This Plugin is defined as all of the files within any archive
	    file or any group of files released in conjunction by the Demigods Team,
	    the Demigods Team, or a derived or modified work based on such files.
	
	 2. A Modification, or a Mod, is defined as this Plugin or a derivative of
	    it with one or more Modification applied to it, or as any program that
	    depends on this Plugin.
	
	 3. Distribution is defined as allowing one or more other people to in
	    any way download or receive a copy of this Plugin, a Modified
	    Plugin, or a derivative of this Plugin.
	
	 4. The Software is defined as an installed copy of this Plugin, a
	    Modified Plugin, or a derivative of this Plugin.
	
	 5. The Demigods Team is defined as Alexander Chauncey and Alex Bennett
	    of http://www.clashnia.com/.
	
	Agreement
	
	 1. Permission is hereby granted to use, copy, modify and/or
	    distribute this Plugin, provided that:
	
	    a. All copyright notices within source files and as generated by
	       the Software as output are retained, unchanged.
	
	    b. Any Distribution of this Plugin, whether as a Modified Plugin
	       or not, includes this license and is released under the terms
	       of this Agreement. This clause is not dependant upon any
	       measure of changes made to this Plugin.
	
	    c. This Plugin, Modified Plugins, and derivative works may not
	       be sold or released under any paid license without explicit 
	       permission from the Demigods Team. Copying fees for the 
	       transport of this Plugin, support fees for installation or
	       other services, and hosting fees for hosting the Software may,
	       however, be imposed.
	
	    d. Any Distribution of this Plugin, whether as a Modified
	       Plugin or not, requires express written consent from the
	       Demigods Team.
	
	 2. You may make Modifications to this Plugin or a derivative of it,
	    and distribute your Modifications in a form that is separate from
	    the Plugin. The following restrictions apply to this type of
	    Modification:
	
	    a. A Modification must not alter or remove any copyright notices
	       in the Software or Plugin, generated or otherwise.
	
	    b. When a Modification to the Plugin is released, a
	       non-exclusive royalty-free right is granted to the Demigods Team
	       to distribute the Modification in future versions of the
	       Plugin provided such versions remain available under the
	       terms of this Agreement in addition to any other license(s) of
	       the initial developer.
	
	    c. Any Distribution of a Modified Plugin or derivative requires
	       express written consent from the Demigods Team.
	
	 3. Permission is hereby also granted to distribute programs which
	    depend on this Plugin, provided that you do not distribute any
	    Modified Plugin without express written consent.
	
	 4. The Demigods Team reserves the right to change the terms of this
	    Agreement at any time, although those changes are not retroactive
	    to past releases, unless redefining the Demigods Team. Failure to
	    receive notification of a change does not make those changes invalid.
	    A current copy of this Agreement can be found included with the Plugin.
	
	 5. This Agreement will terminate automatically if you fail to comply
	    with the limitations described herein. Upon termination, you must
	    destroy all copies of this Plugin, the Software, and any
	    derivatives within 48 hours.
 */

package com.legit2.Demigods.Utilities;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.OfflinePlayer;

import com.legit2.Demigods.Database.DDatabase;

public class DDataUtil 
{
	// Define HashMaps
	private static HashMap<String, HashMap<String, Object>> pluginData = new HashMap<String, HashMap<String, Object>>();
	private static HashMap<String, HashMap<String, Object>> playerData = new HashMap<String, HashMap<String, Object>>();
	private static HashMap<Integer, HashMap<String, Object>> charData = new HashMap<Integer, HashMap<String, Object>>();
	private static HashMap<Integer, HashMap<String, Object>> blockData = new HashMap<Integer, HashMap<String, Object>>();

	/* ---------------------------------------------------
	 *  Begin Plugin Data Methods
	 * ---------------------------------------------------
	 * 
	 *  savePluginData() : Saves (String)dataID to pluginData HashMap.
	 */
	public static boolean savePluginData(String dataID, String dataKey, Object dataValue)
	{
		dataKey = dataKey.toLowerCase();
		
		if(pluginData.containsKey(dataID))
		{
			pluginData.get(dataID).put(dataKey, dataValue);
			return true;
		}
		else
		{
			pluginData.put(dataID, new HashMap<String, Object>());
			pluginData.get(dataID).put(dataKey, dataValue);
			return true;
		}
	}
	
	/*
	 *  removePluginData() : Removes (String)dataID from pluginData HashMap.
	 */
	public static boolean removePluginData(String dataID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(pluginData.containsKey(dataID))
		{
			pluginData.get(dataID).remove(dataKey);
			return true;
		}
		else return false;
	}
	
	/*
	 *  hasPluginData() : Returns true/false according to if (String)dataKey exists for (String)dataID.
	 */
	public static boolean hasPluginData(String dataID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(pluginData.containsKey(dataID))
		{
			if(pluginData.get(dataID).get(dataKey) != null) return true;
			else return false;
		}
		else return false;
	}
	
	/*
	 *  getPluginData() : Returns (Object)dataValue for (int)dataID's (String)dataKey.
	 */
	public static Object getPluginData(String dataID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(pluginData.containsKey(dataID))
		{
			if(pluginData.get(dataID) != null) return pluginData.get(dataID).get(dataKey);
			else return null;
		}
		else return null;
	}
	
	/*
	 *  getAllPluginData() : Returns all pluginData.
	 */
	public static HashMap<String, HashMap<String, Object>> getAllPluginData()
	{
		return pluginData;
	}
	
	/* ---------------------------------------------------
	 *  Begin Block Data Methods
	 * ---------------------------------------------------
	 * 
	 *  saveBlockData() : Saves (int)blockID to blockData HashMap.
	 */
	public static boolean saveBlockData(int blockID, String dataKey, Object dataValue)
	{
		dataKey = dataKey.toLowerCase();
		
		if(blockData.containsKey(blockID))
		{
			blockData.get(blockID).put(dataKey, dataValue);
			return true;
		}
		else
		{
			blockData.put(blockID, new HashMap<String, Object>());
			blockData.get(blockID).put(dataKey, dataValue);
			return true;
		}
	}
	
	/*
	 *  removeBlockData() : Removes (int)blockID from pluginData HashMap.
	 */
	public static boolean removeBlockData(int blockID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(blockData.containsKey(blockID))
		{
			blockData.get(blockID).remove(dataKey);
			return true;
		}
		else return false;
	}
	
	/*
	 *  removeAllBlockData() : Removes (int)blockID from pluginData HashMap.
	 */
	public static boolean removeAllBlockData(int blockID)
	{		
		blockData.remove(blockID);
		return true;
	}
	
	/*
	 *  hasBlockData() : Returns true/false according to if (String)dataKey exists for (int)blockID.
	 */
	public static boolean hasBlockData(int blockID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(blockData.containsKey(blockID))
		{
			if(blockData.get(blockID).get(dataKey) != null) return true;
			else return false;
		}
		else return false;
	}
	
	/*
	 *  getPluginData() : Returns (Object)dataValue for (int)blockID's (String)dataKey.
	 */
	public static Object getBlockData(int blockID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(blockData.containsKey(blockID))
		{
			if(blockData.get(blockID) != null) return blockData.get(blockID).get(dataKey);
			else return null;
		}
		else return null;
	}
	
	/*
	 *  getAllBlockData() : Returns all block data.
	 */
	public static HashMap<Integer, HashMap<String, Object>> getAllBlockData()
	{
		return blockData;
	}
	
	/* ---------------------------------------------------
	 * Begin Player Data Methods
	 * ---------------------------------------------------
	 * 
	 *  savePlayerData() : Saves (String)dataKey to (int)playerID HashMap.
	 */
	public static boolean savePlayerData(OfflinePlayer player, String dataKey, Object dataValue)
	{
		String playerName = player.getName();
		dataKey = dataKey.toLowerCase();
		
		if(playerData.containsKey(playerName))
		{
			playerData.get(playerName).put(dataKey, dataValue);
			return true;
		}
		else return false;
	}
	
	/*
	 *  removePlayerData() : Removes (String)dataKey from (int)playerID's HashMap.
	 */
	public static boolean removePlayerData(OfflinePlayer player, String dataKey)
	{
		String playerName = player.getName();
		dataKey = dataKey.toLowerCase();
		
		if(playerData.containsKey(playerName))
		{
			playerData.get(playerName).remove(dataKey);
			return true;
		}
		else return false;
	}
	
	/*
	 *  hasPlayerData() : Returns true/false according to if (String)dataKey exists for (int)playerID.
	 */
	public static boolean hasPlayerData(OfflinePlayer player, String dataKey)
	{
		String playerName = player.getName();
		dataKey = dataKey.toLowerCase();
		
		if(playerData.containsKey(playerName))
		{
			if(playerData.get(playerName).get(dataKey) != null) return true;
			else return false;
		}
		else return false;
	}
	
	/*
	 *  getPlayerData() : Returns (Object)dataValue for (int)playerID's (String)dataKey.
	 */
	public static Object getPlayerData(OfflinePlayer player, String dataKey)
	{
		String playerName = player.getName();
		dataKey = dataKey.toLowerCase();
		
		if(playerData.containsKey(playerName))
		{
			if(playerData.get(playerName).get(dataKey) != null) return playerData.get(playerName).get(dataKey);
			else return null;
		}
		else return null;
	}
	
	/* ---------------------------------------------------
	 * Begin Character Data Methods
	 * ---------------------------------------------------
	 * 
	 *  charExists() : Returns true/false depening on if the character exists.
	 */
	public static boolean charExists(String charName)
	{
		if(charData.containsKey(charName)) return true;
		else return false;
	}
	
	/*
	 *  charExistsByID() : Returns true/false depening on if the character exists.
	 */
	public static boolean charExistsByID(int charID)
	{		
		if(charData.containsKey(charID)) return true;
		else return false;
	}

	/*
	 *  addChar() : Saves the (int)charID to the charData HashMap.
	 */
	public static boolean addChar(int charID)
	{
		charData.put(charID, new HashMap<String, Object>());
		return true;
	}
	
	/*
	 *  removeChar() : Removes the (int)charID from the charData HashMap.
	 */
	public static boolean removeChar(int charID)
	{
		charData.remove(charID);
		DDatabase.removeChar(charID);
		return true;
	}
	
	/*
	 *  saveCharData() : Saves (String)dataKey to (int)charID HashMap.
	 */
	public static boolean saveCharData(int charID, String dataKey, Object dataValue)
	{
		dataKey = dataKey.toLowerCase();
		
		if(charData.containsKey(charID))
		{
			charData.get(charID).put(dataKey, dataValue);
			return true;
		}
		else return false;
	}
	
	/*
	 *  saveTimedCharData() : Saves (String)dataKey to (int)charID HashMap for (int)seconds.
	 */
	@SuppressWarnings("deprecation")
	public static boolean saveTimedCharData(final int charID, String dataKey, Object dataValue, int seconds)
	{
		int ticksDelayed = seconds * 20;
		final String dataKeyLowercase = dataKey.toLowerCase();
		
		if(charData.containsKey(charID))
		{
			charData.get(charID).put(dataKey, dataValue);
			DMiscUtil.getPlugin().getServer().getScheduler().scheduleAsyncDelayedTask(DMiscUtil.getPlugin(), new Runnable()
			{
				@Override
				public void run()
				{
					removeCharData(charID, dataKeyLowercase);
				}
			}, ticksDelayed);
			return true;
		}
		else return false;
	}
	
	/*
	 *  removeCharData() : Removes (String)dataKey from (int)charID's HashMap.
	 */
	public static boolean removeCharData(int charID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(charData.containsKey(charID))
		{
			charData.get(charID).remove(dataKey);
			return true;
		}
		else return false;
	}
	
	/*
	 *  hashCharData() : Returns true/false according to if (String)dataKey exists for (int)charID.
	 */
	public static boolean hasCharData(int charID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(charData.containsKey(charID))
		{
			if(charData.get(charID).get(dataKey) != null) return true;
			else return false;
		}
		else return false;
	}
	
	/*
	 *  getCharData() : Returns (Object)dataValue for (int)charID's (String)dataKey.
	 */
	public static Object getCharData(int charID, String dataKey)
	{
		dataKey = dataKey.toLowerCase();
		
		if(charData.containsKey(charID))
		{
			if(charData.get(charID).get(dataKey) != null) return charData.get(charID).get(dataKey);
			else return null;
		}
		else return null;
	}
	
	/* ---------------------------------------------------
	 * Begin Miscellaneous Data Methods
	 * ---------------------------------------------------
	 *
	 *  addPlayer() : Saves new (String)username to HashMap playerData.
	 */
	public static boolean addPlayer(OfflinePlayer player, int playerID)
	{
		String playerName = player.getName();

		// Returns false if the player already has the playerData.
		if(newPlayer(player))
		{
			// Creates new player HashMap save.
			playerData.put(playerName, new HashMap<String, Object>());
			return true;
		}
		else return false;
	}
	
	/*
	 *  newPlayer() : Checks to see if (String)username already has HashMap playerData.
	 */
	public static boolean newPlayer(OfflinePlayer player)
	{
		String playerName = player.getName();

		if(playerData.containsKey(playerName)) return false;
		else return true;
	}
	
	/*
	 *  removePlayer() : Removes the (OfflinePlayer)player from the playerData HashMap.
	 */
	public static boolean removePlayer(OfflinePlayer player)
	{
		String playerName = player.getName();;
		playerData.remove(playerName);
		charData.remove(playerName);
		DDatabase.removePlayer(player);
		return true;
	}
	
	/*
	 *  getAllPlayers() : Returns all players in the playerData HashMap.
	 */
	public static HashMap<String, HashMap<String, Object>> getAllPlayers()
	{
		return playerData;
	}
	
	/*
	 *  getAllPlayerData() : Returns all playerData for (Player)player.
	 */
	public static HashMap<String, Object> getAllPlayerData(OfflinePlayer player)
	{
		String playerName = player.getName();;
		return playerData.get(playerName);
	}
	
	/*
	 *  getAllChars() : Returns all players in the playerData HashMap.
	 */
	public static HashMap<Integer, HashMap<String, Object>> getAllChars()
	{
		return charData;
	}
	
	/*
	 *  getAllCharData() : Returns all charData for (int)charID.
	 */
	public static HashMap<String, Object> getAllCharData(int charID)
	{
		return charData.get(charID);
	}
	
	/*
	 *  getAllPlayerChars() : Returns all charData for (int)charID.
	 */
	public static HashMap<Integer, HashMap<String, Object>> getAllPlayerChars(OfflinePlayer player)
	{
		HashMap<Integer, HashMap<String, Object>> temp = new HashMap<Integer, HashMap<String, Object>>();
		int playerID = DPlayerUtil.getPlayerID(player);
		
		for(Entry<Integer, HashMap<String, Object>> characters : getAllChars().entrySet())
		{
			int charID = characters.getKey();
			
			if(characters.getValue().get("char_owner") != null && characters.getValue().get("char_owner").equals(playerID))
			{
				temp.put(charID, characters.getValue());
			}
		}
		return temp;
	}
	
}
