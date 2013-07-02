package com.censoredsoftware.Demigods.Engine.Utility;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ItemUtility
{
	/**
	 * Creates a new item with the given variables.
	 * 
	 * @param material the material that the new item will be.
	 * @param name the name of the new item.
	 * @param lore the lore attached to the new item.
	 * @param enchantments the enchantments attached to the new item.
	 * @return the ItemStack of the newly created item.
	 */
	public static ItemStack create(Material material, String name, List<String> lore, Map<Enchantment, Integer> enchantments)
	{
		// Define variables
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();

		// Set meta data
		if(name != null) meta.setDisplayName(name);
		if(lore != null) meta.setLore(lore);
		item.setItemMeta(meta);

		// Add enchantments if passed in
		if(enchantments != null) item.addUnsafeEnchantments(enchantments);

		return item;
	}

	/**
	 * Creates a book with the given variables.
	 * 
	 * @param title the title of the new book.
	 * @param author the author of the new book.
	 * @param pages the pages of the new book.
	 * @param lore the lore of the new book.
	 * @return the ItemStack of the newly created book.
	 */
	public static ItemStack createBook(String title, String author, List<String> pages, List<String> lore)
	{
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta) book.getItemMeta();

		meta.setTitle(title);
		meta.setAuthor(author);
		meta.setPages(pages);
		if(lore != null) meta.setLore(lore);

		book.setItemMeta(meta);

		return book;
	}

	/**
	 * Creates a chest at <code>location</code> filled with <code>items</code>.
	 * 
	 * @param location the location at which to create the chest.
	 * @param items the ArrayList of items to fill the chest with.
	 */
	public static void createChest(Location location, ArrayList<ItemStack> items)
	{
		// Create the chest
		location.getBlock().setType(Material.CHEST);

		// Get the chest's inventory
		Chest chest = (Chest) location.getBlock().getState();
		Inventory inventory = chest.getBlockInventory();

		// Add the items randomly
		for(ItemStack item : items)
		{
			inventory.setItem((new Random().nextInt(inventory.getSize() - 1) + 1), item);
		}
	}
}
