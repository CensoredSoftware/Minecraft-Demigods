package com.censoredsoftware.demigods.ability.support;

import com.censoredsoftware.demigods.ability.Ability;
import com.censoredsoftware.demigods.deity.Deity;
import com.censoredsoftware.demigods.player.DCharacter;
import com.censoredsoftware.demigods.player.DPlayer;
import com.censoredsoftware.demigods.util.Zones;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Carry implements Ability
{
	private final static String name = "Carry", command = null;
	private final static int cost = 0, delay = 0, repeat = 20;
	private final static List<String> details = Lists.newArrayList("Hold a leash to carry other players on your shoulders.");
	private String deity, permission;
	private boolean needsLead;

	public Carry(final String deity, String permission, boolean needsLead)
	{
		this.deity = deity;
		this.permission = permission;
		this.needsLead = needsLead;
	}

	@Override
	public String getDeity()
	{
		return deity;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getCommand()
	{
		return command;
	}

	@Override
	public String getPermission()
	{
		return permission;
	}

	@Override
	public int getCost()
	{
		return cost;
	}

	@Override
	public int getDelay()
	{
		return delay;
	}

	@Override
	public int getRepeat()
	{
		return repeat;
	}

	@Override
	public List<String> getDetails()
	{
		return details;
	}

	@Override
	public Material getWeapon()
	{
		return null;
	}

	@Override
	public boolean hasWeapon()
	{
		return getWeapon() != null;
	}

	@Override
	public Listener getListener()
	{
		return new Listener()
		{
			@EventHandler(priority = EventPriority.MONITOR)
			private void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event)
			{
				if(Zones.inNoDemigodsZone(event.getPlayer().getLocation()) || !(event.getRightClicked() instanceof Player)) return;
				Player player = event.getPlayer();
				Player clicked = (Player) event.getRightClicked();

				if(Deity.Util.canUseDeitySilent(clicked, deity) && (!needsLead || clicked.getItemInHand().getType().equals(Material.LEASH)) && clicked.getPassenger() == null)
				{
					DCharacter character = DPlayer.Util.getPlayer(player).getCurrent();
					DCharacter clickedChar = DPlayer.Util.getPlayer(clicked).getCurrent();
					if(character == null || clickedChar == null || !DCharacter.Util.areAllied(character, clickedChar)) return;

					clicked.setPassenger(player);
				}
			}

			@EventHandler(priority = EventPriority.HIGHEST)
			private void onPlayerItemHeld(PlayerItemHeldEvent event)
			{
				Player player = event.getPlayer();
				if(Zones.inNoDemigodsZone(player.getLocation())) return;

				if(!Deity.Util.canUseDeitySilent(player, deity) || player.getPassenger() == null) return;

				if(needsLead && player.getInventory().getItem(event.getNewSlot()) != null && !player.getInventory().getItem(event.getNewSlot()).getType().equals(Material.LEASH)) player.getPassenger().leaveVehicle();
				else if(player.getInventory().getItem(event.getNewSlot()) != null) player.getPassenger().leaveVehicle();
			}
		};
	}

	@Override
	public BukkitRunnable getRunnable()
	{
		return null;
	}
}
