package com.github.ajstri.discordtransfer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.github.ajstri.discordtransfer.DiscordTransfer;
import com.github.ajstri.discordtransfer.utils.PluginUtils;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;

/**
 * The Configuration Class for DiscordTransfer
 * @author AJStri
 * @since May 9th, 2017
**/

public class Config {
	
	protected DiscordTransfer discordTransfer;
	protected File dataDir;
	protected File configFile;
	protected Properties config;
	
	/* Configuration Keys */
	
	private final String tokenKey = "token"; // Token of the Discord Bot
	private final String channelKey = "channel"; // Channel Name or ID of the Discord Text Channel
	private final String consoleChannelKey = "console_channel"; // Channel Name of ID of the Discord Text Channel
	private final String consoleRoleKey = "console_role"; // Role Name or ID that can use the Console Channel
	private final String messageFormatKey = "format"; // How the message is formatted
	private final String blockedIdsKey = "blocked_users_id"; // The IDs of the users that cannot relay messages.
	private final String gameStatusKey = "game_status"; // The game status of the bot.
	private final String grematKey = "gremat";
	private final String essentialsKey = "enable_essentials";
	
	// TODO : Add towny_chat
		
	/* Default Values */
	
	private final String tokenValue = "Token Here";
	private final String channelValue = "Channel Name/ID here";
	private final String consoleChannelValue = "Channel Name/ID that acts as Console";
	private final String consoleRoleValue = "The Role Name/ID that can use Console";
	private final String messageFormatValue = "%color%%user%:&r %message%";
	private final String blockedIdsValue = "(id, id, etc)";
	private final String gameStatusValue = "discord bot status";
	private final String grematValue = "true or false";
	private final String essentialsValue = "true or false";

	/**
	 * The Main Configuration Creation
	 * @param discordTransfer2 The Plugin
	 */
	
	public Config(DiscordTransfer discordTransfer2) {
		this.discordTransfer = discordTransfer2;
		this.dataDir = new File(discordTransfer2.getDataFolder().getAbsolutePath().replace("\\", "/") + "/discordcraft");
		this.configFile = new File(dataDir.getAbsolutePath().replace("\\", "/")+"/config.properties");
		
		/* Initialization */
		
		if (!dataDir.exists()) {
			dataDir.mkdirs(); // Create the data directory if it does not exist.
		}
		
		if (!configFile.exists()) { 
			
			/* Create a New Configuration File if it does not exist */
			
			try {
				configFile.createNewFile();
			}
			catch (IOException ioe) {
				// Ignore this
				PluginUtils.warning("IOException upon creating the Config File.");
			}
			
			/* Load the Configuration File */
			
			try {
				FileInputStream fis = new FileInputStream(configFile);
				config.load(fis);
			}
			catch (FileNotFoundException fnfe) {
				/* IMPORTANT */
				PluginUtils.error("Config File not found. Attempting creation");
				try {
					configFile.createNewFile();
				} catch (IOException ioe) {
					PluginUtils.warning("IOException upon creating the Config File.");
					ioe.printStackTrace();
				}
			}
			catch (IOException ioe) {
				/* IMPORTANT */
				PluginUtils.error("IOException upon loading the Config File.");
				ioe.printStackTrace();
			}
			
			/* Set the Properties on the Configuration File 
		  	   Values are defined/explained above */
			
			config.setProperty(tokenKey, tokenValue);
			config.setProperty(channelKey, channelValue);
			config.setProperty(consoleChannelKey, consoleChannelValue);
			config.setProperty(consoleRoleKey, consoleRoleValue);
			config.setProperty(messageFormatKey, messageFormatValue);
			config.setProperty(blockedIdsKey, blockedIdsValue);
			config.setProperty(gameStatusKey, gameStatusValue);
			config.setProperty(grematKey, grematValue);
			config.setProperty(essentialsKey, essentialsValue);
			
			/* Add the Explanation to the file */
			
			try {
				FileOutputStream fos = new FileOutputStream(configFile);
				config.store(fos, 
						"--- DiscordCraft Configuration --- \n"
					  + "This is the Configuration for the DiscordTransfer plugin \n"
					  + "I will explain the fields in here to you \n"
					  + "'" + tokenKey + "' is where you set the Bot-Token \n"
					  + "'" + channelKey + "' is where you set the Channel Name/ID that acts as the Message input/outout \n"
			   		  + "'" + consoleChannelKey + "' is where you set the Channel Name/ID that acts as the Console input/outout \n"
					  + "'" + consoleRoleKey + "' is where you set the Role(s) Name(s)/ID(s) that can access the Console \n"
					  + "To set multiple roles, add _list to the '" + consoleRoleKey + "' property \n"
					  + "Example: " + consoleRoleKey + "= _list(Role1, Role2) \n"
					  + "IT IS VERY IMPORTANT THAT YOU DO INCLUDE THE BRACKETS AND SPACE BETWEEN THE COMMA AND THE NEXT ROLE!!! \n"
					  + "'" + messageFormatKey + "' The Format of the Message in Minecraft and Discord, in Discord colors are ignored. \n"
					  + "For more information, see the instructions on how to set up the DiscordTransfer plugin at: \n"
					  + "http://github.com/ajstri/discordtransfer/wiki"
					  + "--- DiscordCraft Configuration ---");
				// TODO add Blocked IDs to the explanation
			}
			catch (FileNotFoundException fnfe) {
				PluginUtils.error("File not found upon loading the Config File.");
				fnfe.printStackTrace();
			}
			catch (IOException ioe) {
				PluginUtils.warning("IOException upon loading the Config File.");
				ioe.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Add missing properties and reload the Configuration File.
	**/
	
	public void reload () {
		
		/* If a property does not exist, add it */
		
		if (config.getProperty(tokenKey) == null) {
			config.setProperty(tokenKey, tokenValue);
		} 
		if (config.getProperty(channelKey) == null) {
			config.setProperty(channelKey, channelValue);
		}
		if (config.getProperty(consoleChannelKey) == null) {
			config.setProperty(consoleChannelKey, consoleChannelValue);
		}
		if (config.getProperty(consoleRoleKey) == null) {
			config.setProperty(consoleRoleKey, consoleRoleValue);
		}
		if (config.getProperty(messageFormatKey) == null) {
			config.setProperty(messageFormatKey, messageFormatValue);
		}
		if (config.getProperty(blockedIdsKey) == null) {
			config.setProperty(blockedIdsKey, blockedIdsValue);
		}
		if (config.getProperty(gameStatusKey) == null) {
			config.setProperty(gameStatusKey, gameStatusValue);
		}
		if (config.getProperty(grematKey) == null) {
			config.setProperty(grematKey, grematValue);
		}
		if(config.getProperty(essentialsKey, essentialsValue) == null) {
			config.setProperty(essentialsKey, essentialsValue);
		}
		
		/* I really am not sure about this one */
		
		try {
			FileOutputStream fos = new FileOutputStream(configFile);
			config.store(fos, null);
		}
		catch (FileNotFoundException fnfe) {
			PluginUtils.error("File not found upon loading the Config File.");
			fnfe.printStackTrace();
		}
		catch (IOException ioe) {
			PluginUtils.error("IOException upon loading the Config File.");
			ioe.printStackTrace();
		}
		
		/* Load the Configuration File */
		
		try {
			FileInputStream fis = new FileInputStream(configFile);
			config.load(fis);
		}
		catch (IOException ioe) {
			PluginUtils.error("IOException upon loading the Config File.");
			ioe.printStackTrace();
		}
		
	}
	
	/** 
	 * Get the Discord Bot Token as defined in the Configuration File 
	 * @return The token of the Discord Bot. If null, the default value.
	**/
	
	public String getToken() {
		
		/* Check to see if the property Token is null or not.
		 * If it is, just return the default value. */
		if (config.getProperty(messageFormatKey) != null) {
			return config.getProperty(messageFormatKey);
		}
		else { // It's null.
			return null;
		}
		
	}
	
	/**
	 * Get the main Text Channel from the Configuration File.
	 * @return TextChannel as defined in the Configuration File
	**/
	
	public TextChannel getMainChannel() {
		
		String channel;
		Guild g = discordTransfer.bot.jda.getGuilds().get(0);
		
		if (config.getProperty(channelKey) != null) {
			channel = config.getProperty(channelKey);
			if (channel.equals(channelValue)) {
				return null;
			}
			else if (g.getTextChannelById(channel) != null) {
				return g.getTextChannelById(channel);
			}
			else if (g.getTextChannelsByName(channel, false) != null) {
				return g.getTextChannelsByName(channel, false).get(0);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
		
	}
	
	/**
	 * Get the Console Text Channel from the Configuration File.
	 * @return TextChannel as defined in the Configuration File.
	**/
	
	public TextChannel getConsoleChannel() {
		
		String channel;
		Guild g = discordTransfer.bot.jda.getGuilds().get(0);
		
		if (config.getProperty(consoleChannelKey) != null) {
			channel = config.getProperty(consoleChannelKey);
			if (channel.equals(consoleChannelValue)) {
				return null;
			}
			else if (g.getTextChannelById(channel) != null) {
				return g.getTextChannelById(channel);
			}
			else if (g.getTextChannelsByName(channel, false) != null) {
				return g.getTextChannelsByName(channel, false).get(0);
			}
			else {
				return null;
			}
		}
		else {
			return null;
		}
	}
	
	/**
	 * Retrieve the Roles able to use the Console Channel.
	 * @return List<Role> with all roles able to use the console.
	 */
	
	public List<Role> getConsoleRoles() {
		
		Guild g = discordTransfer.bot.getGuild();
		String role = config.getProperty(consoleRoleKey);
		List<Role> roleList = new ArrayList<Role>();
		Role r = null;
		
		/* Check if a value exists. */
		
		if(config.getProperty(consoleRoleKey) != null) {
			if (config.getProperty(consoleRoleKey) == consoleRoleValue) {
				return null; // If it is the default value, nothing will come
			}
			else {
				/* "_list" is the defining sequence for if it is more than one role.
				 * If it does not have it, it is presumably one role. */
				if (!config.getProperty(consoleRoleKey).contains("_list")) {
					/* Check to see if the role exists */
					
					// Check ID
					if (g.getRoleById(role) != null) {
						// Add the role.
						r = g.getRoleById(role);
						roleList.add(r);
					}
					
					// Check Name
					else if (g.getRolesByName(role, false) != null) {
						// Add the role.
						r = g.getRolesByName(role, false).get(0);
						roleList.add(r);
					}
					return roleList;
				}
			
				/* If it has "_list", things get complicated. */
				else if (config.getProperty(consoleRoleKey).contains("_list")) {
					
					/* Separate and check each role to see if it exists */
					
					role.replace("_list(", "").replace(")", "");
					String[] roleSplit = role.split(", ");
					
					// Check each object in the String[]
					for (String rl : roleSplit) {
						Role retRole = null;
						
						// Check ID
						if (g.getRoleById(rl) != null) {
							retRole = g.getRoleById(rl);
							roleList.add(retRole);
						}
						
						// Check Name
						else if (g.getRolesByName(rl, false) != null) {
							retRole = g.getRolesByName(rl, false).get(0);
							roleList.add(retRole);
						}
						return roleList;
					}
				}
			}
		}
		else {
			return null;
		}
		return null;
	}
	
	public String getFormat() {
		
		/* Check to see if the property for Message Format is null or not.
		 * If it is, just return the default value. */
		if (config.getProperty(messageFormatKey) != null) {
			return config.getProperty(messageFormatKey);
		}
		else { // It's null.
			return null;
		}
		
	}
	
	/**
	 * Retrieve the Blocked IDs
	 * @return List<String> of the IDs that are blocked
	**/
	
	public List<String> getBlockedIDs() {
		
		Guild g = discordTransfer.bot.getGuild();
		List<String> guildIDs = new ArrayList<>();
		String origIDs = config.getProperty(blockedIdsKey);
		String[] IDSplit;
		List<String> returnIDs = new ArrayList<>();
		
		/* Get the IDs from the Guild */
		for (Member m : g.getMembers()) {
			String a = m.getUser().getId();
			guildIDs.add(a);
		}
		
		origIDs.replace("(", "").replace(")", "");
		IDSplit = origIDs.split(", ");
		
		for (String s : IDSplit) {
			for (String ss : guildIDs) {
				if (s == ss) {
					returnIDs.add(s);
				}
			}
		}
		
		return returnIDs;
	}
	
	public String getGameStatus() {
		
		String g = config.getProperty(gameStatusKey);
		
		return g;
	}
	
	/**
	 * Return if Essentials is enabled.
	 * @return true if Essentials is enabled, false if not
	**/
	
	public boolean getEssentials() {
		if (config.getProperty(essentialsKey) == null) {
			return false;
		}
		else if(config.getProperty(essentialsKey) == "true") {
			return true;
		}
		else if(config.getProperty(essentialsKey) == "false") {
			return false;
		}
		else {
			return false;
		}
		
	}
	
	/** 
	 * 
	 * @return true if enabled, false if disabled
	**/
	
	public boolean getGremat() {
		if (config.getProperty(grematKey) == null) {
			return false;
		}
		else {
			if (config.getProperty(grematKey) == "true") {
				return true;
			}
			else if (config.getProperty(grematKey) == "false") {
				return false;
			}
			else {
				return false;
			}
		}
	}
	
	/** 
	 * Check if any of the fields in the Configuration File are valid.
	 * If any are null, it is not functional.
	 * @return true if none are null, false if any one of the values are null
	**/
	
	public boolean isFunctional() {
		
		/* If any of these return null, it is not Valid. */
		
		/* Configuration Key Checks */
		if ((config.getProperty(tokenKey) == null)
		 || (config.getProperty(channelKey) == null)
		 || (config.getProperty(consoleChannelKey) == null)
		 || (config.getProperty(consoleRoleKey) == null)
		 || (config.getProperty(messageFormatKey) == null)) {
			return false;
		}
		
		else {
			return true; 
		}
		
	}
	
}
