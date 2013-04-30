package com.censoredsoftware.Demigods.Engine.Listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;

import com.censoredsoftware.Demigods.API.BlockAPI;
import com.censoredsoftware.Demigods.API.ItemAPI;
import com.censoredsoftware.Demigods.API.MiscAPI;
import com.censoredsoftware.Demigods.Demo.Data.Books;
import com.censoredsoftware.Demigods.Engine.Block.Altar;
import com.censoredsoftware.Demigods.Engine.Demigods;
import com.censoredsoftware.Demigods.Engine.DemigodsData;
import com.censoredsoftware.Demigods.Engine.Event.Altar.AltarCreateEvent;
import com.censoredsoftware.Demigods.Engine.Event.Altar.AltarCreateEvent.AltarCreateCause;
import com.censoredsoftware.Demigods.Engine.Event.Misc.ChestSpawnEvent;

public class ChunkListener implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChunkLoad(ChunkLoadEvent event)
	{
		// Define variables
		Location location = MiscAPI.randomChunkLocation(event.getChunk());

		// Let's randomly create chests
		if(DemigodsData.randomPercentBool(Demigods.config.getSettingDouble("generation.chest_chance")) && location.clone().subtract(0, 1, 0).getBlock().getType().isSolid())
		{
			ChestSpawnEvent chestSpawnEvent = new ChestSpawnEvent(location);
			Bukkit.getServer().getPluginManager().callEvent(chestSpawnEvent);
			if(chestSpawnEvent.isCancelled()) return;

			// Define variables
			ArrayList<ItemStack> items = new ArrayList<ItemStack>();

			// Books!
			for(Books book : Books.values())
			{
				if(DemigodsData.randomPercentBool(book.getBook().getSpawnChance())) items.add(book.getBook().getItem());
			}

			// Generate the chest
			ItemAPI.createChest(location, items);
		}

		// If it's a new chunk then we'll generate structures
		if(event.isNewChunk())
		{
			// Choose an arbitrary value and check the chance against it
			if(DemigodsData.randomPercentBool(Demigods.config.getSettingDouble("generation.altar_chance")))
			{
				if(BlockAPI.canGenerateSolid(location, 6))
				{
					// If another Altar doesn't exist nearby then make one
					if(!BlockAPI.altarNearby(location, Demigods.config.getSettingInt("generation.min_blocks_between_altars")))
					{
						AltarCreateEvent altarCreateEvent = new AltarCreateEvent(location, AltarCreateCause.GENERATED);
						Bukkit.getServer().getPluginManager().callEvent(altarCreateEvent);
						if(altarCreateEvent.isCancelled()) return;

						new Altar(DemigodsData.generateInt(5), location);

						location.getWorld().strikeLightningEffect(location);
						location.getWorld().strikeLightningEffect(location);

						for(Entity entity : event.getWorld().getEntities())
						{
							if(entity instanceof Player)
							{
								if(entity.getLocation().distance(location) < 400)
								{
									((Player) entity).sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "An Altar has spawned near you...");
								}
							}
						}
					}
				}
			}
		}
	}
}