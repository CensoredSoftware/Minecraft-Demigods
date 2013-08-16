package com.censoredsoftware.demigods.util;

import com.censoredsoftware.demigods.Demigods;
import com.censoredsoftware.demigods.structure.Structure;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Zones
{
	// TODO Optimize this stuff.

	/**
	 * Returns true if <code>location</code> is within a no-PVP zone.
	 * 
	 * @param location the location to check.
	 * @return true/false depending on if it's a no-PVP zone or not.
	 */
	public static boolean zoneNoPVP(Location location)
	{
		if(Demigods.config.getSettingBoolean("zones.allow_skills_anywhere")) return false;
		if(Demigods.worldguard != null) return !canWorldGuardDynamicPVPAndNotNoPvPStructure(location);
		return Structures.isInRadiusWithFlag(location, Structure.Flag.NO_PVP);
	}

	private static boolean canWorldGuardDynamicPVPAndNotNoPvPStructure(Location location)
	{
		return (!Structures.isInRadiusWithFlag(location, Structure.Flag.NO_PVP)) && canWorldGuardDynamicPVP(location);
	}

	private static boolean canWorldGuardDynamicPVP(Location location)
	{
		ApplicableRegionSet set = Demigods.worldguard.getRegionManager(location.getWorld()).getApplicableRegions(location);
		for(ProtectedRegion region : set)
			if(region.getId().toLowerCase().contains("nopvp")) return false;
		return true;
	}

	/**
	 * Returns true if <code>location</code> is within a no-build zone
	 * for <code>player</code>.
	 * 
	 * @param player the player to check.
	 * @param location the location to check.
	 * @return true/false depending on the position of the <code>player</code>.
	 */
	public static boolean zoneNoBuild(Player player, Location location)
	{
		return !canWorldGuardBuild(player, location);
	}

	private static boolean canWorldGuardBuild(Player player, Location location)
	{
		return Demigods.worldguard.canBuild(player, location);
	}
}
