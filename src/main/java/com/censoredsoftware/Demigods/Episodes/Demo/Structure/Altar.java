package com.censoredsoftware.Demigods.Episodes.Demo.Structure;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import com.censoredsoftware.Demigods.Engine.Demigods;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureBlockData;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureInfo;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureSave;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureSchematic;
import com.censoredsoftware.Demigods.Engine.Utility.*;
import com.censoredsoftware.Demigods.Episodes.Demo.EpisodeDemo;

public class Altar implements StructureInfo
{
	@Override
	public Set<Flag> getFlags()
	{
		return new HashSet<Flag>()
		{
			{
				add(StructureInfo.Flag.NO_PVP_ZONE);
				add(StructureInfo.Flag.PROTECTED_BLOCKS);
				add(StructureInfo.Flag.PRAYER_LOCATION);
			}
		};
	}

	@Override
	public String getStructureType()
	{
		return "Altar";
	}

	@Override
	public Set<StructureSchematic> getSchematics()
	{
		return new HashSet<StructureSchematic>()
		{
			{
				// Create roof
				add(new StructureSchematic(2, 3, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 13));
					}
				}));
				add(new StructureSchematic(-2, 3, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 13));
					}
				}));
				add(new StructureSchematic(2, 3, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 13));
					}
				}));
				add(new StructureSchematic(-2, 3, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 13));
					}
				}));
				add(new StructureSchematic(2, 4, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 4, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 4, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 4, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 5, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-2, 5, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(2, 5, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-2, 5, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(0, 6, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-1, 5, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(-1, 5, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(-1, 5, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(1, 5, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(1, 5, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(1, 5, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(0, 5, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(0, 5, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));
				add(new StructureSchematic(0, 5, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(5), (byte) 1));
					}
				}));

				// Create the enchantment table
				add(new StructureSchematic(0, 2, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.ENCHANTMENT_TABLE));
					}
				}));

				// Create magical table stand
				add(new StructureSchematic(0, 1, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));

				// Create outer steps
				add(new StructureSchematic(3, 0, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(4, 0, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(4, 0, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(4, 0, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(4, 0, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(4, 0, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-3, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-4, 0, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-4, 0, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-4, 0, -0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-4, 0, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-4, 0, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(3, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(2, 0, -4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(1, 0, -4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(0, 0, -4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-1, 0, -4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-2, 0, -4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-3, 0, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-2, 0, 4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-1, 0, 4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(0, 0, 4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(1, 0, 4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(2, 0, 4, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));

				// Create inner steps
				add(new StructureSchematic(3, 0, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 0, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 0, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(1, 0, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(0, 0, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-1, 0, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 0, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 0, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 0, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(1, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(0, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-1, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));

				// Create pillars
				add(new StructureSchematic(3, 0, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 1, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 2, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 3, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 4, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(3, 0, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 1, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 2, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 3, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(3, 4, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(2, 0, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 1, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 2, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 3, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 4, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-2, 0, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 1, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 2, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 3, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 4, 3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-3, 0, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 1, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 2, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 3, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 4, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-3, 0, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 1, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 2, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 3, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-3, 4, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(2, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 1, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 2, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 3, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 4, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-2, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 1, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 2, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 3, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 4, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));

				// Left beam
				add(new StructureSchematic(1, 4, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(0, 4, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98), (byte) 3));
					}
				}));
				add(new StructureSchematic(-1, 4, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(1, 5, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(0, 5, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-1, 5, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));

				// Right beam
				add(new StructureSchematic(1, 4, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(0, 4, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98), (byte) 3));
					}
				}));
				add(new StructureSchematic(-1, 4, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(1, 5, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(0, 5, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-1, 5, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));

				// Top beam
				add(new StructureSchematic(2, 4, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 4, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98), (byte) 3));
					}
				}));
				add(new StructureSchematic(2, 4, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(2, 5, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(2, 5, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(2, 5, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));

				// Bottom beam
				add(new StructureSchematic(-2, 4, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 4, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98), (byte) 3));
					}
				}));
				add(new StructureSchematic(-2, 4, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(98)));
					}
				}));
				add(new StructureSchematic(-2, 5, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-2, 5, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));
				add(new StructureSchematic(-2, 5, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(126), (byte) 1));
					}
				}));

				// Create main platform
				add(new StructureSchematic(2, 1, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(2, 1, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(2, 1, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(2, 1, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(2, 1, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(1, 1, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(0, 1, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-1, 1, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-2, 1, -2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-2, 1, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-2, 1, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-2, 1, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-2, 1, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-1, 1, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(0, 1, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(1, 1, 2, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(1, 1, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(1, 1, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(1, 1, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(0, 1, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-1, 1, -1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-1, 1, 0, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(-1, 1, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
				add(new StructureSchematic(0, 1, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));
			}
		};
	}

	@Override
	public int getRadius()
	{
		return Demigods.config.getSettingInt("zones.altar_radius");
	}

	@Override
	public Location getClickableBlock(Location reference)
	{
		return reference.clone().add(0, 2, 0);
	}

	@Override
	public Listener getUniqueListener()
	{
		return new AltarListener();
	}

	@Override
	public Set<StructureSave> getAll()
	{
		return new HashSet<StructureSave>()
		{
			{
				for(StructureSave saved : StructureUtility.getAllStructureSaves())
				{
					if(saved.getStructureInfo().getStructureType().equals(getStructureType())) add(saved);
				}
			}
		};
	}

	@Override
	public StructureSave createNew(Location reference, boolean generate)
	{
		StructureSave save = new StructureSave();
		save.setReferenceLocation(reference);
		save.setStructureType(getStructureType());
		save.save();
		if(generate) save.generate();
		return save;
	}

	public static boolean altarNearby(Location location)
	{
		for(StructureSave structureSave : StructureUtility.getAllStructureSaves())
		{
			if(structureSave.getReferenceLocation().distance(location) <= Demigods.config.getSettingInt("generation.min_blocks_between_altars") && structureSave.getStructureInfo().getStructureType().equals(EpisodeDemo.Structures.ALTAR.getStructure().getStructureType())) return true;
		}
		return false;
	}
}

class AltarListener implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChunkLoad(final ChunkLoadEvent event)
	{
		// If it's a new chunk then we'll generate structures
		if(event.isNewChunk())
		{
			// Define variables
			final Location location = GenerationUtility.randomChunkLocation(event.getChunk());

			// Check if it can generate
			if(GenerationUtility.canGenerateStrict(location, 3))
			{
				// Return a random boolean based on the chance of Altar generation
				if(MiscUtility.randomPercentBool(Demigods.config.getSettingDouble("generation.altar_chance")))
				{
					// If another Altar doesn't exist nearby then make one
					if(!Altar.altarNearby(location))
					{
						AdminUtility.sendDebug(ChatColor.RED + "Altar generated by SERVER at " + ChatColor.GRAY + "(" + location.getWorld().getName() + ") " + location.getX() + ", " + location.getY() + ", " + location.getZ());

						EpisodeDemo.Structures.ALTAR.getStructure().createNew(location, true);

						location.getWorld().strikeLightningEffect(location);
						location.getWorld().strikeLightningEffect(location);

						Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Demigods.plugin, new Runnable()
						{
							@Override
							public void run()
							{
								for(Entity entity : event.getWorld().getEntities())
								{
									if(entity instanceof Player)
									{
										if(entity.getLocation().distance(location) < 400)
										{
											((Player) entity).sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + Demigods.text.getText(TextUtility.Text.ALTAR_SPAWNED_NEAR));
										}
									}
								}
							}
						}, 1);
					}
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void demigodsAdminWand(PlayerInteractEvent event)
	{
		if(event.getClickedBlock() == null) return;

		// Define variables
		Block clickedBlock = event.getClickedBlock();
		Location location = clickedBlock.getLocation();
		Player player = event.getPlayer();

		/**
		 * Handle Altars
		 */
		if(AdminUtility.useWand(player) && clickedBlock.getType().equals(Material.EMERALD_BLOCK))
		{
			event.setCancelled(true);

			AdminUtility.sendDebug(ChatColor.RED + "Altar generated by ADMIN WAND at " + ChatColor.GRAY + "(" + location.getWorld().getName() + ") " + location.getX() + ", " + location.getY() + ", " + location.getZ());

			player.sendMessage(ChatColor.GRAY + Demigods.text.getText(TextUtility.Text.ADMIN_WAND_GENERATE_ALTAR));
			EpisodeDemo.Structures.ALTAR.getStructure().createNew(location, true);
			player.sendMessage(ChatColor.GREEN + Demigods.text.getText(TextUtility.Text.ADMIN_WAND_GENERATE_ALTAR_COMPLETE));
		}

		if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && AdminUtility.useWand(player) && StructureUtility.partOfStructureWithType(location, "Altar"))
		{
			event.setCancelled(true);

			StructureSave altar = StructureUtility.getStructure(location);

			if(DataUtility.hasTimed(player.getName(), "destroy_altar"))
			{
				AdminUtility.sendDebug(ChatColor.RED + "Altar at " + ChatColor.GRAY + "(" + location.getWorld().getName() + ") " + location.getX() + ", " + location.getY() + ", " + location.getZ() + " removed by " + "ADMIN WAND" + ".");

				// Remove the Altar

				altar.remove();

				DataUtility.removeTimed(player.getName(), "destroy_altar");

				player.sendMessage(ChatColor.GREEN + Demigods.text.getText(TextUtility.Text.ADMIN_WAND_REMOVE_ALTAR_COMPLETE));
			}
			else
			{
				DataUtility.saveTimed(player.getName(), "destroy_altar", true, 5);
				player.sendMessage(ChatColor.RED + Demigods.text.getText(TextUtility.Text.ADMIN_WAND_REMOVE_ALTAR));
			}
		}
	}
}
