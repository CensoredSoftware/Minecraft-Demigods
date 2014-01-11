package com.censoredsoftware.demigods.engine.conversation;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import com.censoredsoftware.censoredlib.helper.ConversationManager;
import com.censoredsoftware.censoredlib.util.Strings;
import com.censoredsoftware.censoredlib.util.Titles;
import com.censoredsoftware.demigods.base.DemigodsConversation;
import com.censoredsoftware.demigods.engine.Demigods;
import com.censoredsoftware.demigods.engine.DemigodsPlugin;
import com.censoredsoftware.demigods.engine.data.Data;
import com.censoredsoftware.demigods.engine.data.serializable.DPlayer;
import com.censoredsoftware.demigods.engine.language.English;
import com.censoredsoftware.demigods.engine.mythos.Structure;
import com.censoredsoftware.demigods.engine.util.Admins;
import com.censoredsoftware.demigods.engine.util.Configs;
import com.censoredsoftware.demigods.engine.util.Messages;
import com.censoredsoftware.demigods.engine.util.Zones;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@SuppressWarnings("unchecked")
public class Administration implements ConversationManager
{
	// Define constants
	private static final String CONTEXT_NAME = "admin_context";

	@Override
	public org.bukkit.event.Listener getUniqueListener()
	{
		return new Listener();
	}

	/**
	 * Defines categories that can be used during administration.
	 */
	public enum Menu
	{
		GENERATE_STRUCTURES('1', new Generation.Menu());

		private final char id;
		private final DemigodsConversation.Category category;

		private Menu(char id, DemigodsConversation.Category category)
		{
			this.id = id;
			this.category = category;
		}

		public char getId()
		{
			return this.id;
		}

		public DemigodsConversation.Category getCategory()
		{
			return this.category;
		}

		public static Menu getFromId(char id)
		{
			for(Menu menu : Administration.Menu.values())
				if(menu.getId() == id) return menu;
			return null;
		}
	}

	@Override
	public Conversation startMenu(Player player)
	{
		return startAdministration(player);
	}

	public static Map<Object, Object> grabRawContext(Player player)
	{
		Map<Object, Object> context = Maps.newHashMap();

		try
		{
			if(!Demigods.Util.isRunningSpigot())
			{
				// Compatibility with vanilla Bukkit
				Field sessionDataField = ConversationContext.class.getDeclaredField("sessionData");
				sessionDataField.setAccessible(true);
				if(Data.hasKeyTemp(player.getName(), CONTEXT_NAME)) context = (Map<Object, Object>) sessionDataField.get(Data.getValueTemp(player.getName(), CONTEXT_NAME));
			}
			else
			{
				// Grab the context Map
				if(Data.hasKeyTemp(player.getName(), CONTEXT_NAME)) context.putAll(((ConversationContext) Data.getValueTemp(player.getName(), CONTEXT_NAME)).getAllSessionData());
			}
		}
		catch(IllegalAccessException | NoSuchFieldException ignored)
		{
			// ignored
		}

		return context;
	}

	public static void saveNotification(ConversationContext context, String key, String notification)
	{
		// Create the data
		if(context.getSessionData(key + "_notifications") == null)
		{
			context.setSessionData(key + "_notifications", Lists.newArrayList());
		}

		// Save the notification
		((List<String>) context.getSessionData(key + "_notifications")).add(notification);
	}

	public static void displayNotifications(ConversationContext context, Player player, String key)
	{
		if(context.getSessionData(key + "_notifications") != null && !((List<String>) context.getSessionData(key + "_notifications")).isEmpty())
		{
			// Grab the notifications
			List<String> notifications = (List<String>) context.getSessionData(key + "_notifications");

			player.sendRawMessage(" ");

			// List them
			for(String notification : notifications)
				player.sendRawMessage("  " + notification);

			// Remove them
			notifications.clear();
		}
	}

	public static Conversation startAdministration(Player player)
	{
		// Build the conversation and begin
		Conversation conversation = Demigods.CONVERSATION_FACTORY.withEscapeSequence("/exit").withLocalEcho(false).withInitialSessionData(grabRawContext(player)).withFirstPrompt(new StartAdministration()).buildConversation(player);

		// Save the context
		DPlayer.Util.saveAdministrationContext(player, conversation.getContext());

		// Begin
		conversation.begin();

		return conversation;
	}

	// Main menu
	static class StartAdministration extends ValidatingPrompt
	{
		@Override
		public String getPromptText(ConversationContext context)
		{
			// Define variables
			Player player = (Player) context.getForWhom();

			// Clear chat
			Messages.clearRawChat(player);

			// Send menu
			Messages.clearRawChat(player);
			player.sendRawMessage(ChatColor.RED + " -- Administration -------------------------------------");
			player.sendRawMessage(" ");
			for(String message : English.ADMINISTRATION_INTRO.getLines())
				player.sendRawMessage(message);
			player.sendRawMessage(" ");
			player.sendRawMessage(ChatColor.GRAY + " To begin, choose an option by entering its number in the chat:");
			player.sendRawMessage(" ");

			// Send menu options
			for(Menu menu : Administration.Menu.values())
				if(menu.getCategory().canUse(context)) player.sendRawMessage(ChatColor.GRAY + "   [" + menu.getId() + ".] " + menu.getCategory().getChatName(context));

			// Send menu intro footer
			player.sendRawMessage(" ");
			player.sendRawMessage(English.ADMINISTRATION_INTRO_FOOTER.getLine());

			// Display notifications if available
			displayNotifications(context, player, "menu");

			return "";
		}

		@Override
		protected boolean isInputValid(ConversationContext context, String message)
		{
			try
			{
				Menu menu = Administration.Menu.getFromId(Character.toUpperCase(message.charAt(0)));
				return menu != null && menu.getCategory().canUse(context) || "leave".equalsIgnoreCase(message);
			}
			catch(Exception ignored)
			{
				// ignored
			}
			return false;
		}

		@Override
		protected Prompt acceptValidatedInput(ConversationContext context, String message)
		{
			// Handle leaving
			if("leave".equalsIgnoreCase(message))
			{
				// Toggle everything off
				DPlayer.Util.toggleAdministration((Player) context.getForWhom(), false, true);
				return null;
			}

			// Otherwise return the chosen prompt
			return Administration.Menu.getFromId(Character.toUpperCase(message.charAt(0))).getCategory();
		}

	}

	/**
	 * Houses all conversation methods related to generation (e.g. structures) that are used in the Administration menu.
	 */
	static class Generation
	{
		// Main structures menu for the Administration menu
		static class Menu extends ValidatingPrompt implements DemigodsConversation.Category
		{
			@Override
			public String getChatName(ConversationContext context)
			{
				return ChatColor.GREEN + "Generate Structure";
			}

			@Override
			public boolean canUse(ConversationContext context)
			{
				return ((Player) context.getForWhom()).hasPermission("demigods.admin.structurewand") && !Admins.structureWandEnabled((Player) context.getForWhom());
			}

			private static Map<Integer, Structure> getStructureChoices(ConversationContext context)
			{
				// Grab the data (that may or may not be there)
				Object data = context.getSessionData("generable_structures");

				// Return it if it exists
				if(data != null)
				{
					return (Map<Integer, Structure>) data;
				}
				else
				{
					int count = 1;
					Map<Integer, Structure> structures = Maps.newHashMap();

					for(Structure structure : Demigods.mythos().getStructures())
					{
						// Only list that shiz if it needs to be listed, dawg
						if(structure.getFlags().contains(Structure.Flag.STRUCTURE_WAND_GENERABLE))
						{
							// Add it to the choices
							structures.put(count, structure);

							// Count up
							count++;
						}
					}

					// Save it and return it
					context.setSessionData("generable_structures", structures);
					return structures;
				}
			}

			@Override
			public String getPromptText(ConversationContext context)
			{
				// Define variables
				Player player = (Player) context.getForWhom();

				// Send the messages
				Messages.clearRawChat(player);
				player.sendRawMessage(ChatColor.YELLOW + Titles.chatTitle("Structure Chooser"));
				player.sendRawMessage(" ");
				for(String string : English.ADMINISTRATION_GENERATE_STRUCTURE_INTRO.getLines())
				{
					player.sendRawMessage(string);
				}
				player.sendRawMessage(" ");

				// List the generable structures and save a Hash Map for storing the structures with identifiers for choosing
				for(Map.Entry<Integer, Structure> structure : getStructureChoices(context).entrySet())
				{
					player.sendRawMessage(ChatColor.GRAY + "   [" + structure.getKey() + ".] " + ChatColor.GOLD + structure.getValue().getName());
				}

				// Type menu message
				player.sendRawMessage(" ");
				player.sendRawMessage(English.ADMINISTRATION_TYPE_MENU.getLine());

				// Display notifications if available
				displayNotifications(context, player, this.getClass().getSimpleName());

				return "";
			}

			@Override
			protected boolean isInputValid(ConversationContext context, String message)
			{
				try
				{
					return "menu".equalsIgnoreCase(message) || getStructureChoices(context).keySet().contains(Integer.parseInt(message));
				}
				catch(NumberFormatException ignored)
				{
					return false;
				}
			}

			@Override
			protected Prompt acceptValidatedInput(ConversationContext context, String message)
			{
				if("menu".equalsIgnoreCase(message))
				{
					// Return to main menu
					return new StartAdministration();
				}
				else
				{
					// Save the chosen structure to the data
					context.setSessionData("chosen_structure", getStructureChoices(context).get(Integer.parseInt(message)));

					// Return next menu depending on the chosen structure
					if(((Structure) context.getSessionData("chosen_structure")).getDesigns().size() > 1)
					{
						return new Design();
					}
					else
					{
						return new Selection();
					}
				}
			}
		}

		// Design selection
		static class Design extends ValidatingPrompt
		{
			private static Structure getChosenStructure(ConversationContext context)
			{
				return (Structure) context.getSessionData("chosen_structure");
			}

			private static Map<Integer, Structure.Design> getDesignChoices(ConversationContext context)
			{
				int count = 1;
				Map<Integer, Structure.Design> designs = Maps.newHashMap();

				for(Structure.Design design : getChosenStructure(context).getDesigns())
				{
					// Add the design to the map along with it's count
					designs.put(count, design);

					// Count up
					count++;
				}

				// Return the designs
				return designs;
			}

			@Override
			public String getPromptText(ConversationContext context)
			{
				// Define variables
				Player player = (Player) context.getForWhom();

				// Get the structure
				Structure structure = getChosenStructure(context);

				// Send the messages
				Messages.clearRawChat(player);
				player.sendRawMessage(ChatColor.YELLOW + Titles.chatTitle("Design Selection"));
				player.sendRawMessage(" ");
				for(String string : English.ADMINISTRATION_STRUCTURE_DESIGN_SELECTION.getLines())
				{
					player.sendRawMessage(string.replace("{structure}", structure.getName()));
				}
				player.sendRawMessage(" ");

				// Display the design choices
				for(Map.Entry<Integer, Structure.Design> design : getDesignChoices(context).entrySet())
				{
					player.sendRawMessage(ChatColor.GRAY + "   [" + design.getKey() + ".] " + ChatColor.LIGHT_PURPLE + Strings.beautify(design.getValue().getName()));
				}

				return "";
			}

			@Override
			protected boolean isInputValid(ConversationContext context, String message)
			{
				try
				{
					return "menu".equalsIgnoreCase(message) || getDesignChoices(context).keySet().contains(Integer.parseInt(message));
				}
				catch(NumberFormatException ignored)
				{
					return false;
				}
			}

			@Override
			protected Prompt acceptValidatedInput(ConversationContext context, String message)
			{
				if("menu".equalsIgnoreCase(message))
				{
					// Return to main menu
					return new StartAdministration();
				}
				else
				{
					// Save the chosen structure to the data
					context.setSessionData("chosen_design", getDesignChoices(context).get(Integer.parseInt(message)));

					// Send them to the selection
					return new Selection();
				}
			}
		}

		// Structure wand selection
		static class Selection extends ValidatingPrompt
		{
			private static Structure getChosenStructure(ConversationContext context)
			{
				return (Structure) context.getSessionData("chosen_structure");
			}

			@Override
			public String getPromptText(ConversationContext context)
			{
				// Define variables
				Player player = (Player) context.getForWhom();

				// Get the structure
				Structure structure = getChosenStructure(context);

				// Enable the wand
				Admins.toggleStructureWand(player, true);

				// Send the messages
				Messages.clearRawChat(player);
				player.sendRawMessage(ChatColor.YELLOW + Titles.chatTitle("Structure Wand"));
				player.sendRawMessage(" ");

				if(structure.getRequiredGenerationPoints() == 1)
				{
					for(String string : English.ADMINISTRATION_STRUCTURE_WAND_ENABLED_1_POINT.getLines())
					{
						player.sendRawMessage(string.replace("{item}", Material.getMaterial(Configs.getSettingInt("admin.structure_wand_tool")).name()).replace("{structure}", structure.getName()));
					}
				}
				else
				{
					for(String string : English.ADMINISTRATION_STRUCTURE_WAND_ENABLED_2_POINTS.getLines())
					{
						player.sendRawMessage(string.replace("{item}", Material.getMaterial(Configs.getSettingInt("admin.structure_wand_tool")).name()).replace("{structure}", structure.getName()));
					}
				}

				// Display notifications if available
				displayNotifications(context, player, this.getClass().getSimpleName());

				return "";
			}

			@Override
			protected boolean isInputValid(ConversationContext context, String message)
			{
				return "menu".equalsIgnoreCase(message) || "continue".equalsIgnoreCase(message);
			}

			@Override
			protected Prompt acceptValidatedInput(ConversationContext context, String message)
			{
				// Define variables
				Player player = (Player) context.getForWhom();

				// Get the structure
				Structure structure = getChosenStructure(context);

				if("menu".equalsIgnoreCase(message))
				{
					// Disable wand
					Admins.toggleStructureWand(player, false);

					// Return to main menu
					return new StartAdministration();
				}
				else if("continue".equalsIgnoreCase(message))
				{
					// Get locations from context
					Object locObj1 = context.getSessionData("structurewand_loc1");
					Object locObj2 = context.getSessionData("structurewand_loc2");
					String design = ((Structure.Design) context.getSessionData("chosen_design")).getName();

					// Ensure that selections have been made
					if(structure.getRequiredGenerationPoints() == 1 && locObj1 != null)
					{
						// Cast the object
						Location loc1 = (Location) locObj1;

						// Create the structure
						scheduleGeneration(structure, design, loc1);

						// Success boi
						return success(context);
					}
					else if(structure.getRequiredGenerationPoints() == 2 && locObj1 != null && locObj2 != null)
					{
						// Cast the object
						Location loc1 = (Location) locObj1;
						Location loc2 = (Location) locObj2;

						// Create the structure
						scheduleGeneration(structure, design, loc1, loc2);

						// Ye ye ye ye turtle man
						return success(context);
					}
					else
					{
						// Add notification
						saveNotification(context, this.getClass().getSimpleName(), English.ADMINISTRATION_LOCATIONS_NOT_SELECTED.getLine());
					}
				}

				return new Selection();
			}

			private static Prompt success(ConversationContext context)
			{
				// All good, toggle wand off
				Admins.toggleStructureWand((Player) context.getForWhom(), false);

				// Save notification
				saveNotification(context, Menu.class.getSimpleName(), English.ADMINISTRATION_STRUCTURE_GENERATED.getLine());

				// Set data to null
				context.setSessionData("structurewand_loc1", null);
				context.setSessionData("structurewand_loc2", null);

				return new Menu();
			}

			private static void scheduleGeneration(final Structure structure, @Nullable final String design, final Location... locations)
			{
				// This must be schedules synchronously because chat is handled asynchronously
				Bukkit.getScheduler().scheduleSyncDelayedTask(DemigodsPlugin.plugin(), new Runnable()
				{
					@Override
					public void run()
					{
						structure.createNew(true, design, locations);
					}
				});
			}
		}
	}

	public static class Listener implements org.bukkit.event.Listener
	{
		/**
		 * Listens for the PlayerInteract event and uses it strictly for the structure wand.
		 * 
		 * @param event the PlayerInteractEvent to monitor
		 */
		@EventHandler(priority = EventPriority.MONITOR)
		private void onStructureWand(PlayerInteractEvent event)
		{
			// Check some requirements
			if(event.getClickedBlock() == null || Zones.inNoDemigodsZone(event.getPlayer().getLocation()) || !Admins.useStructureWand(event.getPlayer())) return;

			// All good, handle the wand
			Player player = event.getPlayer();

			// Grab the context
			ConversationContext context = DPlayer.Util.getAdministrationContext(player);

			// Save the blocks
			if(event.getAction() == Action.LEFT_CLICK_BLOCK)
			{
				Location location = event.getClickedBlock().getLocation();

				context.setSessionData("structurewand_loc1", location);
				player.sendRawMessage(English.ADMINISTRATION_STRUCTUREWAND_LEFT.getLine().replace("{x}", "" + location.getX()).replace("{y}", "" + location.getY()).replace("{z}", "" + location.getZ()));
				player.sendRawMessage(" ");
			}
			else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
			{
				Location location = event.getClickedBlock().getLocation();

				context.setSessionData("structurewand_loc2", location);
				player.sendRawMessage(English.ADMINISTRATION_STRUCTUREWAND_RIGHT.getLine().replace("{x}", "" + location.getX()).replace("{y}", "" + location.getY()).replace("{z}", "" + location.getZ()));
				player.sendRawMessage(" ");
			}

			// Cancel the event
			event.setCancelled(true);
		}

		@EventHandler(priority = EventPriority.MONITOR)
		private void onPlayerMove(PlayerMoveEvent event)
		{
			// Define variables
			Player player = event.getPlayer();

			// Remind them that they're administrating
			if(DPlayer.Util.isAdministrating(player) && System.currentTimeMillis() % 4000 < 1000)
			{
				player.sendMessage(English.ADMINISTRATION_STILL_IN_MENU.getLine());
			}
		}
	}
}
