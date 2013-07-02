package com.censoredsoftware.Demigods.Episodes.Demo.Structure;

import com.censoredsoftware.Demigods.Engine.Demigods;
import com.censoredsoftware.Demigods.Engine.Object.Deity.Deity;
import com.censoredsoftware.Demigods.Engine.Object.General.DemigodsLocation;
import com.censoredsoftware.Demigods.Engine.Object.General.DemigodsPlayer;
import com.censoredsoftware.Demigods.Engine.Object.PlayerCharacter.PlayerCharacter;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureBlockData;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureInfo;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureSave;
import com.censoredsoftware.Demigods.Engine.Object.Structure.StructureSchematic;
import com.censoredsoftware.Demigods.Engine.Utility.*;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

				add(new StructureSchematic(3, 0, 3, new HashSet<StructureBlockData>()
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
				add(new StructureSchematic(3, 0, -3, new HashSet<StructureBlockData>()
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
				add(new StructureSchematic(-3, 0, -3, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));

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

				// Top left of platform
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
				add(new StructureSchematic(1, 1, -1, new HashSet<StructureBlockData>()
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

				// Top right of platform
				add(new StructureSchematic(2, 1, 2, new HashSet<StructureBlockData>()
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
				add(new StructureSchematic(2, 1, 1, new HashSet<StructureBlockData>()
				{
					{
						add(new StructureBlockData(Material.getMaterial(44), (byte) 5));
					}
				}));

				// Bottom left of platform
				add(new StructureSchematic(-2, 1, -2, new HashSet<StructureBlockData>()
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
				add(new StructureSchematic(-1, 1, -1, new HashSet<StructureBlockData>()
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

				// Bottom right of platform
				add(new StructureSchematic(-2, 1, 2, new HashSet<StructureBlockData>()
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
				add(new StructureSchematic(-1, 1, 1, new HashSet<StructureBlockData>()
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
	public void createNew(Location reference, boolean generate)
	{
		StructureSave save = new StructureSave();
		save.setReferenceLocation(reference);
		save.setStructureType(getStructureType());
		save.save();
		if(generate) save.generate();
	}
}

class AltarListener implements Listener
{
	// TODO Move prayer menu to Structure Listener.

	@EventHandler(priority = EventPriority.HIGH)
	public void altarInteract(PlayerInteractEvent event)
	{
		if(event.getClickedBlock() == null || event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

		// Define variables
		Player player = event.getPlayer();

		// First we check if the player is in an Altar and return if not
		if(com.censoredsoftware.Demigods.Engine.Object.Structure.Old.Altar.isAltar(event.getClickedBlock().getLocation()))
		{
			if(event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE) && !DemigodsPlayer.isPraying(player))
			{
				if(Demigods.config.getSettingBoolean("zones.use_dynamic_pvp_zones") && ZoneUtility.canTarget(player))
				{
					player.sendMessage(ChatColor.GRAY + "You cannot use an Altar when PvP is still possible.");
					player.sendMessage(ChatColor.GRAY + "Wait a few moments and then try again when it's safe.");
					event.setCancelled(true);
					return;
				}
				DemigodsPlayer.togglePraying(player, true);

				// First we clear chat
				MiscUtility.clearChat(player);

				// Tell nearby players that the user is praying
				for(Entity entity : player.getNearbyEntities(16, 16, 16))
				{
					if(entity instanceof Player) ((Player) entity).sendMessage(ChatColor.AQUA + player.getName() + " has knelt at a nearby Altar.");
				}

				player.sendMessage(ChatColor.AQUA + " -- Prayer Menu --------------------------------------");

				altarMenu(player);

				// If they are in the process of creating a character we'll just skip them to the confirm screen
				if(DataUtility.hasKeyTemp(player.getName(), "temp_createchar_finalstep") && Boolean.parseBoolean(DataUtility.getValueTemp(player.getName(), "temp_createchar_finalstep").toString()))
				{
					MiscUtility.clearChat(player);
					finalConfirmDeity(player);
				}

				event.setCancelled(true);
			}
			else if(event.getClickedBlock().getType().equals(Material.ENCHANTMENT_TABLE) && DemigodsPlayer.isPraying(player))
			{
				DemigodsPlayer.togglePraying(player, false);

				// Clear whatever is being worked on in this Pray session
				DataUtility.removeTemp(player.getName(), "temp_createchar");

				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void altarChatEvent(AsyncPlayerChatEvent event)
	{
		// Define variables
		Player player = event.getPlayer();
		Location location = player.getLocation();

		// First we check if the player is in/time an Altar and currently praying, if not we'll return
		if(ZoneUtility.zoneAltar(location) != null && DemigodsPlayer.isPraying(player))
		{
			// Cancel their chat
			event.setCancelled(true);

			// Define variables
			String message = event.getMessage();

			// Return to main menu
			if(message.equalsIgnoreCase("x") || message.startsWith("abort") || message.equalsIgnoreCase("menu") || message.equalsIgnoreCase("exit"))
			{
				// Remove now useless data
				DataUtility.removeTemp(player.getName(), "temp_createchar");

				MiscUtility.clearChat(player);

				player.sendMessage(ChatColor.YELLOW + " " + UnicodeUtility.rightwardArrow() + " Main Menu ----------------------------------------");
				player.sendMessage(" ");

				altarMenu(player);
				return;
			}

			// Create Character
			if(message.equals("1") || message.contains("create") && message.contains("character"))
			{
				MiscUtility.clearChat(player);

				player.sendMessage(ChatColor.YELLOW + " " + UnicodeUtility.rightwardArrow() + " Creating Character --------------------------------");
				player.sendMessage(" ");

				chooseName(player);
				return;
			}

			/*
			 * Character creation sub-steps
			 */
			if(DataUtility.hasKeyTemp(player.getName(), "temp_createchar"))
			{
				// Step 1 of character creation
				if(DataUtility.getValueTemp(player.getName(), "temp_createchar").equals("choose_name"))
				{
					confirmName(player, message);
					return;
				}

				// Step 2 of character creation
				if(DataUtility.getValueTemp(player.getName(), "temp_createchar").equals("confirm_name"))
				{
					if(message.equalsIgnoreCase("y") || message.contains("yes"))
					{
						chooseDeity(player);
						return;
					}
					else
					{
						chooseName(player);
						return;
					}
				}

				// Step 3 of character creation
				if(DataUtility.getValueTemp(player.getName(), "temp_createchar").equals("choose_deity"))
				{
					confirmDeity(player, message);
					return;
				}

				// Step 4 of character creation
				if(DataUtility.getValueTemp(player.getName(), "temp_createchar").equals("confirm_deity"))
				{
					if(message.equalsIgnoreCase("y") || message.contains("yes"))
					{
						deityConfirmed(player);
						return;
					}
					else
					{
						chooseDeity(player);
						return;
					}
				}

				// Step 5 of character creation
				if(DataUtility.getValueTemp(player.getName(), "temp_createchar").equals("confirm_all"))
				{
					if(message.equalsIgnoreCase("y") || message.contains("yes"))
					{
						Inventory ii = Bukkit.getServer().createInventory(player, 27, "Place Your Tributes Here");
						player.openInventory(ii);
					}
					else
					{
						MiscUtility.clearChat(player);
						player.sendMessage(ChatColor.YELLOW + " " + UnicodeUtility.rightwardArrow() + " Main Menu ----------------------------------------");
						player.sendMessage(" ");
						altarMenu(player);
					}
				}
			}

			// View Characters
			else if(message.equals("2") || message.startsWith("view") && message.contains("characters"))
			{
				MiscUtility.clearChat(player);

				player.sendMessage(ChatColor.YELLOW + " " + UnicodeUtility.rightwardArrow() + " Viewing Characters --------------------------------");
				player.sendMessage(" ");

				viewChars(player);
			}
			// View Warps
			else if(message.equals("3") || message.startsWith("view") && message.contains("warps"))
			{
				if(DemigodsPlayer.getPlayer(player).getCurrent() == null) return;

				MiscUtility.clearChat(player);

				player.sendMessage(ChatColor.YELLOW + " " + UnicodeUtility.rightwardArrow() + " Viewing Warps --------------------------------");
				player.sendMessage(" ");

				viewWarps(player);
			}
			// View Characters
			else if(message.equals("4") || message.startsWith("view") && message.contains("invites"))
			{
				if(DemigodsPlayer.getPlayer(player).getCurrent() == null || !DemigodsLocation.hasInvites(DemigodsPlayer.getPlayer(player).getCurrent())) return;

				MiscUtility.clearChat(player);

				player.sendMessage(ChatColor.YELLOW + " " + UnicodeUtility.rightwardArrow() + " Viewing Invites --------------------------------");
				player.sendMessage(" ");

				viewInvites(player);
			}
			else if(message.contains("info"))
			{
				MiscUtility.clearChat(player);

				// Define variables
				String charName = message.replace(" info", "").trim();
				PlayerCharacter character = PlayerCharacter.getCharacterByName(charName);

				viewChar(player, character);
			}

			// Switch Character
			else if(message.startsWith("switch to"))
			{
				MiscUtility.clearChat(player);

				// Define variables
				String charName = message.replace("switch to ", "").trim();

				switchChar(player, charName);
			}

			// Warp Name
			else if(message.startsWith("name warp"))
			{
				// Define variables
				String name = message.replace("name warp", "").trim();

				nameAltar(player, name);
			}

			// Warp Invite
			else if(message.startsWith("invite"))
			{
				// Define variables
				String name = message.replace("invite", "").trim();

				inviteWarp(player, name);
			}

			// Invite Accept
			else if(message.startsWith("accept invite"))
			{
				// Define variables
				String name = message.replace("accept invite", "").trim();

				acceptInvite(player, name);
			}

			// Warp Character
			else if(message.startsWith("warp to"))
			{
				// Define variables
				String warpName = message.replace("warp to ", "").trim();

				AltarListener.warpChar(player, warpName);
			}
		}

	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void createCharacter(InventoryCloseEvent event)
	{
		try
		{
			if(!(event.getPlayer() instanceof Player)) return;
			Player player = (Player) event.getPlayer();

			// If it isn't a confirmation chest then exit
			if(!event.getInventory().getName().contains("Place Your Tributes Here")) return;

			// Exit if this isn't for character creation
			if(!DemigodsPlayer.isPraying(player) || !DataUtility.hasKeyTemp(player.getName(), "temp_createchar_finalstep") || !Boolean.parseBoolean(DataUtility.getValueTemp(player.getName(), "temp_createchar_finalstep").toString())) return;

			// Define variables
			String chosenName = DataUtility.getValueTemp(player.getName(), "temp_createchar_name").toString();
			String chosenDeity = DataUtility.getValueTemp(player.getName(), "temp_createchar_deity").toString();
			String deityAlliance = MiscUtility.capitalize(Deity.getDeity(chosenDeity).getInfo().getAlliance());

			// Check the chest items
			int items = 0;
			int neededItems = Deity.getDeity(chosenDeity).getInfo().getClaimItems().size();

			for(ItemStack ii : event.getInventory().getContents())
			{
				if(ii != null)
				{
					for(Material item : Deity.getDeity(chosenDeity).getInfo().getClaimItems())
					{
						if(ii.getType().equals(item))
						{
							items++;
						}
					}
				}
			}

			player.sendMessage(ChatColor.YELLOW + "The " + deityAlliance + "s are pondering your offerings...");
			if(neededItems == items)
			{
				// They were accepted, finish everything up!
				PlayerCharacter.create(player, chosenDeity, chosenName, true);

				// Remove temporary data
				DataUtility.removeTemp(player.getName(), "temp_createchar");

				// Stop their praying, enable movement, enable chat
				DemigodsPlayer.togglePraying(player, false);

				// Remove old data now
				DataUtility.removeTemp(player.getName(), "temp_createchar_finalstep");
				DataUtility.removeTemp(player.getName(), "temp_createchar_name");
				DataUtility.removeTemp(player.getName(), "temp_createchar_deity");
			}
			else
			{
				player.sendMessage(ChatColor.RED + "You have been denied entry into the lineage of " + chosenDeity + "!");
			}

			// Clear the confirmation case
			event.getInventory().clear();
		}
		catch(Exception e)
		{
			// Print error for debugging
			e.printStackTrace();
		}
	}

	// Method for use within Altars
	protected static void altarMenu(Player player)
	{
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + " While using an Altar you are unable chat with players.");
		player.sendMessage(ChatColor.GRAY + " You can return to the main menu at anytime by typing \"menu\".");
		player.sendMessage(ChatColor.GRAY + " Right-click the Altar again or walk away to stop Praying.");
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + " To begin, choose an option by entering it's number in the chat:");
		player.sendMessage(" ");

		if(DataUtility.hasKeyTemp(player.getName(), "temp_createchar"))
		{
			player.sendMessage(ChatColor.GRAY + "   [X.] " + ChatColor.RED + "Abort Character Creation");
		}
		else player.sendMessage(ChatColor.GRAY + "   [1.] " + ChatColor.GREEN + "Create New Character");

		player.sendMessage(ChatColor.GRAY + "   [2.] " + ChatColor.YELLOW + "View Characters");

		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();
		if(character != null)
		{
			player.sendMessage(ChatColor.GRAY + "   [3.] " + ChatColor.BLUE + "View Warps");
			if(DemigodsLocation.hasInvites(character)) player.sendMessage(ChatColor.GRAY + "   [4.] " + ChatColor.DARK_PURPLE + "View Invites");
			player.sendMessage(" ");
			player.sendMessage(ChatColor.GRAY + " Type" + ChatColor.YELLOW + " invite <character name> " + ChatColor.GRAY + "to invite another player here.");
		}

		player.sendMessage(" ");
	}

	// View characters
	protected static void viewChars(Player player)
	{
		final List<PlayerCharacter> chars = DemigodsPlayer.getChars(player);

		if(chars.isEmpty())
		{
			player.sendMessage(ChatColor.GRAY + "  You have no characters. Why not go make one?");
			player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " create character" + ChatColor.GRAY + " to do so.");
			player.sendMessage(" ");
			return;
		}

		player.sendMessage(ChatColor.LIGHT_PURPLE + "  Light purple " + ChatColor.GRAY + "represents your current character.");
		player.sendMessage(" ");

		for(PlayerCharacter character : chars)
		{
			String color = "";
			String name = character.getName();
			Deity deity = character.getDeity();
			int favor = character.getMeta().getFavor();
			int maxFavor = character.getMeta().getMaxFavor();
			ChatColor favorColor = MiscUtility.getColor(character.getMeta().getFavor(), character.getMeta().getMaxFavor());
			int ascensions = character.getMeta().getAscensions();

			if(character.isActive()) color = ChatColor.LIGHT_PURPLE + "";

			player.sendMessage(ChatColor.GRAY + "  " + ChatColor.GRAY + color + name + ChatColor.GRAY + " [" + deity.getInfo().getColor() + deity + ChatColor.GRAY + " / Fav: " + favorColor + favor + ChatColor.GRAY + " (of " + ChatColor.GREEN + maxFavor + ChatColor.GRAY + ") / Asc: " + ChatColor.GREEN + ascensions + ChatColor.GRAY + "]");
		}

		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " <character name> info" + ChatColor.GRAY + " for detailed information. ");
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " switch to <character name> " + ChatColor.GRAY + "to change your current");
		player.sendMessage(ChatColor.GRAY + "  character.");
		player.sendMessage(" ");
	}

	// View warps
	protected static void viewWarps(Player player)
	{
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();
		if(character.getWarps().isEmpty())
		{
			player.sendMessage(ChatColor.GRAY + "  You have no Altar warps. Why not go make one?");
			player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " name warp <warp name>" + ChatColor.GRAY + " to name a warp here.");
			player.sendMessage(" ");
			return;
		}

		player.sendMessage(ChatColor.LIGHT_PURPLE + "  Light purple " + ChatColor.GRAY + "represents the closest warp.");
		player.sendMessage(" ");
		boolean hasWarp = false;

		for(Map.Entry<DemigodsLocation, String> warp : character.getWarps().entrySet())
		{
			String color = "";
			String name = warp.getValue();
			int X = (int) warp.getKey().toLocation().getX();
			int Y = (int) warp.getKey().toLocation().getY();
			int Z = (int) warp.getKey().toLocation().getZ();
			String world = warp.getKey().toLocation().getWorld().getName().toUpperCase();

			if(ZoneUtility.zoneAltar(warp.getKey().toLocation()) == ZoneUtility.zoneAltar(player.getLocation()))
			{
				color = ChatColor.LIGHT_PURPLE + "";
				hasWarp = true;
			}

			player.sendMessage("  " + color + name + ChatColor.GRAY + " [" + "X: " + ChatColor.GREEN + X + ChatColor.GRAY + " / Y: " + ChatColor.GREEN + Y + ChatColor.GRAY + " / Z: " + ChatColor.GREEN + Z + ChatColor.GRAY + " / World: " + ChatColor.GREEN + world + ChatColor.GRAY + "]");
		}

		player.sendMessage(" ");

		player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " warp to <warp name> " + ChatColor.GRAY + "to warp.");
		if(!hasWarp) player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " name warp <warp name>" + ChatColor.GRAY + " to name a warp here.");
		else player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " name warp <warp name>" + ChatColor.GRAY + " to rename this warp.");
		player.sendMessage(" ");
	}

	// View warps
	protected static void viewInvites(Player player)
	{
		for(Map.Entry<DemigodsLocation, String> invite : DemigodsPlayer.getPlayer(player).getCurrent().getInvites().entrySet())
		{
			player.sendMessage(ChatColor.GRAY + "  " + invite.getValue());
		}

		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + "  Type" + ChatColor.YELLOW + " accept invite <invite name> " + ChatColor.GRAY + "to warp.");
		player.sendMessage(" ");
	}

	// View character
	protected static void viewChar(Player player, PlayerCharacter character)
	{
		// Return if the character doesn't exist
		if(character == null)
		{
			player.sendMessage(ChatColor.RED + "That character doesn't exist.");
			return;
		}

		// Define variables
		String status;

		// Send the player the info
		player.sendMessage(ChatColor.YELLOW + " " + UnicodeUtility.rightwardArrow() + " Viewing Character ---------------------------------");
		player.sendMessage(" ");

		if(character.isActive())
		{
			status = ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "(Current) " + ChatColor.RESET;
		}
		else
		{
			status = ChatColor.RED + "" + ChatColor.ITALIC + "(Inactive) " + ChatColor.RESET;
		}

		player.sendMessage("    " + status + ChatColor.YELLOW + character.getName() + ChatColor.GRAY + " > Allied to " + character.getDeity().getInfo().getColor() + character.getDeity() + ChatColor.GRAY + " of the " + ChatColor.GOLD + character.getAlliance() + "s");
		player.sendMessage(ChatColor.GRAY + "  --------------------------------------------------");
		player.sendMessage(ChatColor.GRAY + "    Health: " + ChatColor.WHITE + MiscUtility.getColor(character.getHealth(), 20) + character.getHealth() + ChatColor.GRAY + " (of " + ChatColor.GREEN + 20 + ChatColor.GRAY + ")" + ChatColor.GRAY + "  |  Hunger: " + ChatColor.WHITE + MiscUtility.getColor(character.getHunger(), 20) + character.getHunger() + ChatColor.GRAY + " (of " + ChatColor.GREEN + 20 + ChatColor.GRAY + ")" + ChatColor.GRAY + "  |  Experience: " + ChatColor.WHITE + (int) (Math.round(character.getExperience()))); // TODO: Exp isn't correct.
		player.sendMessage(ChatColor.GRAY + "  --------------------------------------------------");
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + "    Favor: " + MiscUtility.getColor(character.getMeta().getFavor(), character.getMeta().getMaxFavor()) + character.getMeta().getFavor() + ChatColor.GRAY + " (of " + ChatColor.GREEN + character.getMeta().getMaxFavor() + ChatColor.GRAY + ") " + ChatColor.YELLOW + "+5 every " + Demigods.config.getSettingInt("regeneration.favor") + " seconds"); // TODO: This should change with "perks" (assuming that we implement faster favor regeneration perks).
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + "    Ascensions: " + ChatColor.GREEN + character.getMeta().getAscensions());
		player.sendMessage(" ");

	}

	protected static void switchChar(Player player, String charName)
	{
		PlayerCharacter newChar = PlayerCharacter.getCharacterByName(charName);

		if(newChar != null)
		{
			// Make sure they aren't trying to switch to their current character
			if(DemigodsPlayer.getPlayer(player).getCurrent().getName().equals(newChar.getName()))
			{
				player.sendMessage(ChatColor.RED + "You can't switch to your current character.");
				return;
			}

			DemigodsPlayer.getPlayer(player).switchCharacter(newChar);

			player.setDisplayName(newChar.getDeity().getInfo().getColor() + newChar.getName() + ChatColor.WHITE);
			player.setPlayerListName(newChar.getDeity().getInfo().getColor() + newChar.getName() + ChatColor.WHITE);

			// Save their previous character and chat number for later monitoring
			DataUtility.saveTemp(player.getName(), "temp_chat_number", 0);

			// Disable prayer
			DemigodsPlayer.togglePraying(player, false);
		}
		else
		{
			player.sendMessage(ChatColor.RED + "Your current character couldn't be changed...");
			player.sendMessage(ChatColor.RED + "Please let an admin know.");
			DemigodsPlayer.togglePrayingSilent(player, false);
		}
	}

	// Choose name
	protected static void chooseName(Player player)
	{
		DataUtility.saveTemp(player.getName(), "temp_createchar", "choose_name");
		player.sendMessage(ChatColor.AQUA + "  Enter a name: " + ChatColor.GRAY + "(Alpha-Numeric Only)");
		player.sendMessage(" ");
	}

	// Name confirmation
	protected static void confirmName(Player player, String message)
	{
		int maxCaps = Demigods.config.getSettingInt("character.max_caps_in_name");
		if(message.length() >= 15 || !StringUtils.isAlphanumeric(message) || DemigodsPlayer.hasCharName(player, message) || MiscUtility.hasCapitalLetters(message, maxCaps))
		{
			// Validate the name
			DataUtility.saveTemp(player.getName(), "temp_createchar", "choose_name");
			if(message.length() >= 15) player.sendMessage(ChatColor.RED + "  That name is too long.");
			if(DemigodsPlayer.hasCharName(player, message)) player.sendMessage(ChatColor.RED + "  You already have a character with that name.");
			if(!StringUtils.isAlphanumeric(message)) player.sendMessage(ChatColor.RED + "  You can only use Alpha-Numeric characters.");
			if(MiscUtility.hasCapitalLetters(message, maxCaps)) player.sendMessage(ChatColor.RED + "  Too many capital letters. You can only have " + maxCaps + ".");
			player.sendMessage(ChatColor.AQUA + "  Enter a different name: " + ChatColor.GRAY + "(Alpha-Numeric Only)");
			player.sendMessage(" ");
		}
		else
		{
			DataUtility.saveTemp(player.getName(), "temp_createchar", "confirm_name");
			String chosenName = message.replace(" ", "");
			player.sendMessage(ChatColor.AQUA + "  Are you sure you want to use " + ChatColor.YELLOW + chosenName + ChatColor.AQUA + "?" + ChatColor.GRAY + " (y/n)");
			player.sendMessage(" ");
			DataUtility.saveTemp(player.getName(), "temp_createchar_name", chosenName);
		}
	}

	// Choose deity
	protected static void chooseDeity(Player player)
	{
		player.sendMessage(ChatColor.AQUA + "  Please choose a Deity: " + ChatColor.GRAY + "(Type in the name of the Deity)");
		for(String alliance : Deity.getLoadedDeityAlliances())
		{
			for(Deity deity : Deity.getAllDeitiesInAlliance(alliance))
				player.sendMessage(ChatColor.GRAY + "  " + UnicodeUtility.rightwardArrow() + " " + ChatColor.YELLOW + MiscUtility.capitalize(deity.getInfo().getName()) + ChatColor.GRAY + " (" + alliance + ")");
		}
		player.sendMessage(" ");

		DataUtility.saveTemp(player.getName(), "temp_createchar", "choose_deity");
	}

	// Deity confirmation
	protected static void confirmDeity(Player player, String message)
	{
		// Check their chosen Deity
		for(String alliance : Deity.getLoadedDeityAlliances())
		{
			for(Deity deity : Deity.getAllDeitiesInAlliance(alliance))
			{
				if(message.equalsIgnoreCase(deity.getInfo().getName()))
				{
					// Their chosen deity matches an existing deity, ask for confirmation
					String chosenDeity = message.replace(" ", "");
					player.sendMessage(ChatColor.AQUA + "  Are you sure you want to use " + ChatColor.YELLOW + MiscUtility.capitalize(chosenDeity) + ChatColor.AQUA + "?" + ChatColor.GRAY + " (y/n)");
					player.sendMessage(" ");
					DataUtility.saveTemp(player.getName(), "temp_createchar_deity", MiscUtility.capitalize(chosenDeity.toLowerCase()));
					DataUtility.saveTemp(player.getName(), "temp_createchar", "confirm_deity");
					return;
				}
			}
		}
		if(message.equalsIgnoreCase("_Alex"))
		{
			player.sendMessage(ChatColor.AQUA + "  Well you can't be _Alex... but he is awesome!");
			player.sendMessage(" ");

			// They can't be _Alex silly! Make them re-choose
			chooseDeity(player);
		}
		if(message.equalsIgnoreCase("HmmmQuestionMark") || message.equalsIgnoreCase("HQM"))
		{
			player.sendMessage(ChatColor.AQUA + "  Well you can't be HQM... but he is awesome!");
			player.sendMessage(" ");

			// They can't be HQM silly! Make them re-choose
			chooseDeity(player);
		}
	}

	// Confirmed deity
	@SuppressWarnings("unchecked")
	protected static void deityConfirmed(Player player)
	{
		// Define variables
		String chosenDeity = DataUtility.getValueTemp(player.getName(), "temp_createchar_deity").toString();

		// They accepted the Deity choice, now ask them to input their items so they can be accepted
		player.sendMessage(ChatColor.AQUA + "  Before you can confirm your lineage with " + ChatColor.YELLOW + chosenDeity + ChatColor.AQUA + ",");
		player.sendMessage(ChatColor.AQUA + "  you must first sacrifice the following items:");
		player.sendMessage(" ");
		for(Material item : Deity.getDeity(chosenDeity).getInfo().getClaimItems())
		{
			player.sendMessage(ChatColor.GRAY + "  " + UnicodeUtility.rightwardArrow() + " " + ChatColor.YELLOW + item.name());
		}
		player.sendMessage(" ");
		player.sendMessage(ChatColor.GRAY + "  After you obtain these items, return to an Altar to");
		player.sendMessage(ChatColor.GRAY + "  confirm your new character.");
		player.sendMessage(" ");

		DataUtility.saveTemp(player.getName(), "temp_createchar_finalstep", true);
	}

	// Final confirmation of deity
	@SuppressWarnings("unchecked")
	protected static void finalConfirmDeity(Player player)
	{
		// Define variables
		String chosenDeity = DataUtility.getValueTemp(player.getName(), "temp_createchar_deity").toString();

		// Save data
		DataUtility.saveTemp(player.getName(), "temp_createchar_finalstep", true);
		DataUtility.saveTemp(player.getName(), "temp_createchar", "confirm_all");

		// Send them the chat
		player.sendMessage(ChatColor.GREEN + " " + UnicodeUtility.rightwardArrow() + " Confirming Character -------------------------------");
		player.sendMessage(" ");
		player.sendMessage(ChatColor.AQUA + "  Do you have the following items in your inventory?" + ChatColor.GRAY + " (y/n)");
		player.sendMessage(" ");
		for(Material item : Deity.getDeity(chosenDeity).getInfo().getClaimItems())
		{
			player.sendMessage(ChatColor.GRAY + "  " + UnicodeUtility.rightwardArrow() + " " + ChatColor.YELLOW + item.name());
		}
		player.sendMessage(" ");
	}

	protected static void nameAltar(Player player, String name) // TODO Make warps store differently.
	{
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();
		if(character.getWarps().isEmpty())
		{
			// Save named DemigodsLocation for warp.
			character.addWarp(DemigodsLocation.create(player.getLocation()), name);
			player.sendMessage(ChatColor.GRAY + "Your warp to this altar was named: " + ChatColor.YELLOW + name.toUpperCase() + ChatColor.GRAY + ".");
			return;
		}

		// Check for same names
		for(String warp : character.getWarps().values())
		{
			if(warp.equalsIgnoreCase(name))
			{
				player.sendMessage(ChatColor.GRAY + "A warp by that name already exists.");
				return;
			}
		}

		// Check for same altars
		for(DemigodsLocation warp : character.getWarps().keySet())
		{
			if(ZoneUtility.zoneAltar(warp.toLocation()) == ZoneUtility.zoneAltar(player.getLocation()))
			{
				character.removeWarp(warp);
			}
		}

		// Save named DemigodsLocation for warp.
		character.addWarp(DemigodsLocation.create(player.getLocation()), name);
		player.sendMessage(ChatColor.GRAY + "Your warp to this Altar was named: " + ChatColor.YELLOW + name.toUpperCase() + ChatColor.GRAY + ".");
	}

	protected static void inviteWarp(Player player, String name)
	{
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();
		PlayerCharacter invited = PlayerCharacter.getCharacterByName(name);

		if(character == null) return;
		else if(invited == null)
		{
			player.sendMessage(" ");
			player.sendMessage(ChatColor.GRAY + "No such character exists, try again.");
			return;
		}
		else if(!invited.getOfflinePlayer().isOnline() || invited.getOfflinePlayer() == character.getOfflinePlayer())
		{
			player.sendMessage(" ");
			player.sendMessage(invited.getDeity().getInfo().getColor() + invited.getName() + ChatColor.GRAY + " must be online to receive an invite.");
			return;
		}
		else if(!character.getAlliance().equalsIgnoreCase(invited.getAlliance()))
		{
			player.sendMessage(" ");
			player.sendMessage(invited.getDeity().getInfo().getColor() + invited.getName() + ChatColor.GRAY + " must be in your alliance to receive an invite.");
			return;
		}

		if(DemigodsLocation.alreadyInvited(character, invited)) DemigodsLocation.removeInvite(character, DemigodsLocation.getInvite(character, invited));

		DemigodsLocation.addInvite(character, invited);
		DemigodsPlayer.togglePraying(player, false);
		MiscUtility.clearChat(player);

		player.sendMessage(invited.getDeity().getInfo().getColor() + invited.getName() + ChatColor.GRAY + " has been invited to this Altar.");
		invited.getOfflinePlayer().getPlayer().sendMessage(invited.getDeity().getInfo().getColor() + character.getName() + ChatColor.GRAY + " has invited you to an Altar!");
		invited.getOfflinePlayer().getPlayer().sendMessage(ChatColor.GRAY + "Head to a nearby Altar and " + ChatColor.DARK_PURPLE + "View Invites" + ChatColor.GRAY + ".");
	}

	protected static void acceptInvite(Player player, String name)
	{
		PlayerCharacter character = DemigodsPlayer.getPlayer(player).getCurrent();
		DemigodsLocation invite = DemigodsLocation.getInvite(character, name);

		if(invite != null)
		{
			DemigodsPlayer.togglePraying(player, false);
			MiscUtility.clearChat(player);

			player.teleport(invite.toLocation());

			// TODO player.sendMessage(ChatColor.GRAY + "Warp to " + ChatColor.YELLOW + invite.getName().toUpperCase() + ChatColor.GRAY + " complete.");

			DemigodsLocation.removeInvite(character, invite);
			return;
		}
		player.sendMessage(ChatColor.GRAY + "No invite by that name exists, try again.");
	}

	protected static void warpChar(Player player, String warpName)
	{
		for(Map.Entry<DemigodsLocation, String> warp : DemigodsPlayer.getPlayer(player).getCurrent().getWarps().entrySet())
		{
			if(warp.getValue().equals(warpName.toUpperCase()))
			{
				DemigodsPlayer.togglePraying(player, false);
				MiscUtility.clearChat(player);

				player.teleport(warp.getKey().toLocation());

				player.sendMessage(ChatColor.GRAY + "Warp to " + ChatColor.YELLOW + warpName.toUpperCase() + ChatColor.GRAY + " complete.");
				return;
			}
		}
		player.sendMessage(ChatColor.GRAY + "No warp by that name exists, try again.");
	}
}
