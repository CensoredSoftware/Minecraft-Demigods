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

package com.legit2.Demigods;

import org.bukkit.inventory.ItemStack;

public class DTributeValue
{
	/*
	 *  getTributeValue : Returns an int of the value of the (ItemStack)item.
	 */
	public static int getTributeValue(ItemStack itemStack)
	{
		int val = 0;
		if(itemStack == null) return 0;
		switch(itemStack.getType())
		{
			case STONE: val+=itemStack.getAmount()*0.5; break;
			case COBBLESTONE: val+=itemStack.getAmount()*0.3; break;
			case DIRT: val+=itemStack.getAmount()*0.1; break;
			case LOG: val+=itemStack.getAmount()*1; break;
			case WOOD: val+=itemStack.getAmount()*0.23; break;
			case STICK: val+=itemStack.getAmount()*0.11; break;
			case GLASS: val+=itemStack.getAmount()*1.5; break;
			case LAPIS_BLOCK: val+=itemStack.getAmount()*85; break;
			case SANDSTONE: val+=itemStack.getAmount()*0.9; break;
			case GOLD_BLOCK: val+=itemStack.getAmount()*170; break;
			case IRON_BLOCK: val+=itemStack.getAmount()*120; break;
			case BRICK: val+=itemStack.getAmount()*10; break;
			case TNT: val+=itemStack.getAmount()*10; break;
			case MOSSY_COBBLESTONE: val+=itemStack.getAmount()*10; break;
			case OBSIDIAN: val+=itemStack.getAmount()*10; break;
			case DIAMOND_BLOCK: val+=itemStack.getAmount()*300; break;
			case CACTUS: val+=itemStack.getAmount()*1.7; break;
			case YELLOW_FLOWER: val+=itemStack.getAmount()*0.1; break;
			case SEEDS: val+=itemStack.getAmount()*0.3; break;
			case PUMPKIN: val+=itemStack.getAmount()*0.7; break;
			case CAKE: val+=itemStack.getAmount()*22; break;
			case APPLE: val+=itemStack.getAmount()*5; break;
			case COAL: val+=itemStack.getAmount()*2.5; break;
			case DIAMOND: val+=itemStack.getAmount()*30; break;
			case IRON_ORE: val+=itemStack.getAmount()*7; break;
			case GOLD_ORE: val+=itemStack.getAmount()*13; break;
			case IRON_INGOT: val+=itemStack.getAmount()*12; break;
			case GOLD_INGOT: val+=itemStack.getAmount()*18; break;
			case STRING: val+=itemStack.getAmount()*2.4; break;
			case WHEAT: val+=itemStack.getAmount()*0.6; break;
			case BREAD: val+=itemStack.getAmount()*2; break;
			case RAW_FISH: val+=itemStack.getAmount()*2.4; break;
			case PORK: val+=itemStack.getAmount()*2.4; break;
			case COOKED_FISH: val+=itemStack.getAmount()*3.4; break;
			case GRILLED_PORK: val+=itemStack.getAmount()*3.4; break;
			case GOLDEN_APPLE: val+=itemStack.getAmount()*190; break;
			case GOLD_RECORD: val+=itemStack.getAmount()*60; break;
			case GREEN_RECORD: val+=itemStack.getAmount()*60; break;
			case GLOWSTONE: val+=itemStack.getAmount()*1.7; break;
			case REDSTONE: val+=itemStack.getAmount()*3.3; break;
			case EGG: val+=itemStack.getAmount()*0.3; break;
			case SUGAR: val+=itemStack.getAmount()*1.2; break;
			case BONE: val+=itemStack.getAmount()*3; break;
			case ENDER_PEARL: val+=itemStack.getAmount()*1.7; break;
			case SULPHUR: val+=itemStack.getAmount()*1.2; break;
			case COCOA: val+=itemStack.getAmount()*0.6; break;
			case ROTTEN_FLESH: val+=itemStack.getAmount()*3; break;
			case RAW_CHICKEN: val+=itemStack.getAmount()*2; break;
			case COOKED_CHICKEN: val+=itemStack.getAmount()*2.6; break;
			case RAW_BEEF: val+=itemStack.getAmount()*2; break;
			case COOKED_BEEF: val+=itemStack.getAmount()*2.7; break;
			case MELON: val+=itemStack.getAmount()*0.8; break;
			case COOKIE: val+=itemStack.getAmount()*0.45; break;
			case VINE: val+=itemStack.getAmount()*1.2; break;
			case EMERALD: val+=itemStack.getAmount()*7; break;
			case EMERALD_BLOCK: val+=itemStack.getAmount()*70; break;
			case DRAGON_EGG: val+=itemStack.getAmount()*10000; break;
			
			default: val += itemStack.getAmount() * 0.1; break;
		}
		return val;
	}
}
