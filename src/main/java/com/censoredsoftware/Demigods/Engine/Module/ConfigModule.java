package com.censoredsoftware.Demigods.Engine.Module;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Module to load configuration settings from any passed in demigods's config.yml.
 */
public class ConfigModule
{
	private static Plugin plugin;

	/**
	 * Constructor to create a new ConfigModule for the given demigods's <code>instance</code>.
	 * 
	 * @param instance The demigods instance the ConfigModule attaches to.
	 * @param copyDefaults Boolean for copying the default config.yml found inside this demigods over the config file utilized by this library.
	 */
	public ConfigModule(Plugin instance, boolean copyDefaults)
	{
		plugin = instance;
		Configuration config = plugin.getConfig();
		config.options().copyDefaults(copyDefaults);
		plugin.saveConfig();
	}

	/**
	 * Retrieve the Integer setting for String <code>id</code>.
	 * 
	 * @param id The String key for the setting.
	 * @return Integer setting.
	 */
	public int getSettingInt(String id)
	{
		if(plugin.getConfig().isInt(id)) return plugin.getConfig().getInt(id);
		else return -1;
	}

	/**
	 * Retrieve the String setting for String <code>id</code>.
	 * 
	 * @param id The String key for the setting.
	 * @return String setting.
	 */
	public String getSettingString(String id)
	{
		if(plugin.getConfig().isString(id)) return plugin.getConfig().getString(id);
		else return null;
	}

	/**
	 * Retrieve the Boolean setting for String <code>id</code>.
	 * 
	 * @param id The String key for the setting.
	 * @return Boolean setting.
	 */
	public boolean getSettingBoolean(String id)
	{
		return !plugin.getConfig().isBoolean(id) || plugin.getConfig().getBoolean(id);
	}

	/**
	 * Retrieve the Double setting for String <code>id</code>.
	 * 
	 * @param id The String key for the setting.
	 * @return Double setting.
	 */
	public double getSettingDouble(String id)
	{
		if(plugin.getConfig().isDouble(id)) return plugin.getConfig().getDouble(id);
		else return -1;
	}

	/**
	 * Retrieve the List<String> setting for String <code>id</code>.
	 * 
	 * @param id The String key for the setting.
	 * @return List<String> setting.
	 */
	public List<String> getSettingArrayListString(String id)
	{
		List<String> strings = new ArrayList<String>();
		if(plugin.getConfig().isList(id))
		{
			for(String s : plugin.getConfig().getStringList(id))
				strings.add(s);
			return strings;
		}
		else return null;
	}
}
