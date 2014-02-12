package com.demigodsrpg.demigods.engine.data;

import com.demigodsrpg.demigods.engine.DemigodsPlugin;
import com.demigodsrpg.demigods.engine.data.file.FileDataManager;

import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

public abstract class DataManager
{
	// TODO Let people register these as a service, just like Mythos.

	static final DataManager DATA_MANAGER;
	static
	{
		// Get the correct data manager.
		String saveMethod = DemigodsPlugin.getInst().getConfig().getString("saving.method", "file");
		switch(saveMethod.toLowerCase())
		{
			case "file":
			{
				DATA_MANAGER = trainManager(FileDataManager.class);
				break;
			}
			default:
			{
				DemigodsPlugin.getInst().getLogger().severe("\"" + saveMethod + "\" is not a valid save method.");
				DemigodsPlugin.getInst().getLogger().severe("Defaulting to file save method.");
				DATA_MANAGER = trainManager(FileDataManager.class);
				break;
			}
		}
	}

	private static DataManager trainManager(Class<? extends DataManager> manager)
	{
		try
		{
			return manager.newInstance();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	static final WorldDataManager WORLD_DATA_MANAGER = new WorldDataManager();

	protected abstract void init();

	protected abstract void save();

	protected abstract void flushData() throws AccessDeniedException;

	protected abstract <V extends DataAccess<K, V>, K> V getFor(Class<V> clazz, K key);

	protected abstract <K, V extends DataAccess<K, V>> Collection<V> getAllOf(Class<V> clazz);

	protected abstract <K, V extends DataAccess<K, V>> ConcurrentMap<K, V> getMapFor(Class<V> clazz);

	protected abstract <K, V extends DataAccess<K, V>> void putFor(Class<V> clazz, K key, V value);

	protected abstract <K, V extends DataAccess<K, V>> void removeFor(Class<V> clazz, K key);

	public static void initAllData()
	{
		WORLD_DATA_MANAGER.init();
		DATA_MANAGER.init();
	}

	public static void saveAllData()
	{
		WORLD_DATA_MANAGER.save();
		DATA_MANAGER.save();
	}

	public static void flushAllData() throws AccessDeniedException
	{
		WORLD_DATA_MANAGER.flushData();
		DATA_MANAGER.flushData();
	}
}