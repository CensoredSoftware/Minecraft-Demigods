package com.censoredsoftware.demigods.listener;

// TODO Fix for lag.

import com.censoredsoftware.demigods.Demigods;
import com.censoredsoftware.demigods.location.DLocation;
import com.censoredsoftware.demigods.player.DCharacter;
import com.censoredsoftware.demigods.player.DPlayer;
import com.censoredsoftware.demigods.structure.Structure;
import com.censoredsoftware.demigods.util.Structures;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class GriefListener implements Listener
{
	private final static Set<Material> blockInventories = new HashSet<Material>()
	{
		{
			add(Material.CHEST);
			add(Material.ENDER_CHEST);
			add(Material.FURNACE);
			add(Material.BURNING_FURNACE);
			add(Material.DISPENSER);
			add(Material.DROPPER);
			add(Material.BREWING_STAND);
			add(Material.BEACON);
			add(Material.HOPPER);
			add(Material.HOPPER_MINECART);
			add(Material.STORAGE_MINECART);
		}
	};

	@EventHandler(priority = EventPriority.HIGHEST)
	// TODO MINOR LAG - NOT SURE
	public void onBlockPlace(BlockPlaceEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		Structure.Save save = Structures.getInRadiusWithFlag(event.getBlock().getLocation(), Structure.Flag.NO_GRIEFING);
		if(save != null)
		{
			DCharacter character = DPlayer.Util.getPlayer(event.getPlayer()).getCurrent();
			DCharacter owner = save.getOwner();
			if(character != null && owner != null && character.getId().equals(owner.getId())) return;
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		Structure.Save save = Structures.getInRadiusWithFlag(event.getBlock().getLocation(), Structure.Flag.NO_GRIEFING);
		if(save != null)
		{
			DCharacter character = DPlayer.Util.getPlayer(event.getPlayer()).getCurrent();
			DCharacter owner = save.getOwner();
			if(character != null && owner != null && character.getId().equals(owner.getId())) return;
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockIgnite(BlockIgniteEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		Structure.Save save = Structures.getInRadiusWithFlag(event.getBlock().getLocation(), Structure.Flag.NO_GRIEFING);
		if(save != null)
		{
			DCharacter character = DPlayer.Util.getPlayer(event.getPlayer()).getCurrent();
			DCharacter owner = save.getOwner();
			if(character != null && owner != null && character.getId().equals(owner.getId())) return;
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBurn(BlockBurnEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		if(Structures.isInRadiusWithFlag(event.getBlock().getLocation(), Structure.Flag.NO_GRIEFING)) event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	// TODO MINOR LAG
	public void onBlockFall(EntityChangeBlockEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		if(event.getEntityType() != EntityType.FALLING_BLOCK || event.getBlock().getRelative(BlockFace.DOWN).equals(Material.AIR)) return;
		FallingBlock block = (FallingBlock) event.getEntity();
		Location blockLocation = block.getLocation();
		if(Structures.isInRadiusWithFlag(DLocation.Util.getFloorBelowLocation(block.getLocation()), Structure.Flag.NO_GRIEFING))
		{
			// Break the block
			event.setCancelled(true);
			event.getBlock().setType(Material.AIR);
			blockLocation.getWorld().dropItemNaturally(blockLocation, new ItemStack(block.getMaterial()));
			block.remove();
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLiquidMove(BlockFromToEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		if(Structures.isInRadiusWithFlag(event.getToBlock().getLocation(), Structure.Flag.NO_GRIEFING)) event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPistonExtend(BlockPistonExtendEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		boolean in = false;
		boolean out = false;
		for(Block block : event.getBlocks())
		{
			if(Structures.isInRadiusWithFlag(block.getLocation(), Structure.Flag.NO_GRIEFING)) in = true;
			else out = true;
		}
		if(in != out) event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPistonRetract(BlockPistonRetractEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		boolean block = Structures.isInRadiusWithFlag(event.getBlock().getLocation(), Structure.Flag.NO_GRIEFING);
		boolean retract = Structures.isInRadiusWithFlag(event.getRetractLocation(), Structure.Flag.NO_GRIEFING);
		if(block != retract) event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	// TODO MINOR LAG - NOT SURE
	public void onBlockDamage(BlockDamageEvent event)
	{
		if(Demigods.isDisabledWorld(event.getBlock().getLocation())) return;
		Structure.Save save = Structures.getInRadiusWithFlag(event.getBlock().getLocation(), Structure.Flag.NO_GRIEFING);
		if(save != null)
		{
			DCharacter character = DPlayer.Util.getPlayer(event.getPlayer()).getCurrent();
			DCharacter owner = save.getOwner();
			if(character != null && owner != null && character.getId().equals(owner.getId())) return;
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityExplode(final EntityExplodeEvent event)
	{
		if(Demigods.isDisabledWorld(event.getEntity().getLocation())) return;
		if(Structures.isInRadiusWithFlag(event.getLocation(), Structure.Flag.NO_GRIEFING)) event.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onAttemptInventoryOpen(PlayerInteractEvent event) // TODO Fix horse inventories.
	{
		if(Demigods.isDisabledWorld(event.getPlayer().getLocation())) return;
		if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
		Block block = event.getClickedBlock();
		Structure.Save save = Structures.getInRadiusWithFlag(block.getLocation(), Structure.Flag.NO_GRIEFING);
		if(save == null) return;
		if(blockInventories.contains(block.getType()))
		{
			DCharacter character = DPlayer.Util.getPlayer(event.getPlayer()).getCurrent();
			DCharacter owner = save.getOwner();
			if(character != null && owner != null && character.getId().equals(owner.getId())) return;
			event.setCancelled(true);
		}
	}
}
