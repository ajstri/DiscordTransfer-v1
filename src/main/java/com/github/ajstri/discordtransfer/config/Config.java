package com.github.ajstri.discordtransfer.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.github.ajstri.discordtransfer.DiscordTransfer;

import net.dv8tion.jda.core.entities.Guild;
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
		
		/* Default Values */
	
	private final String tokenValue = "Token Here";
	private final String channelValue = "Channel Name/ID here";
	private final String consoleChannelValue = "Channel Name/ID that acts as Console";
	private final String consoleRoleValue = "The Role Name/ID that can use Console";
	private final String messageFormatValue = "%color%%user%:&r %message%";

	public Config(DiscordTransfer discordTransfer) {
		this.discordTransfer = discordTransfer;
		this.dataDir = new File(discordTransfer.getDataFolder().getAbsolutePath().replace("\\", "/") + "/discordcraft");
		this.configFile = new File(dataDir.getAbsolutePath().replace("\\", "/")+"/config.properties");
		
		/* Initialization */
		
		if (!dataDir.exists()) dataDir.mkdirs(); // Create the data directory if it does not exist.
		
		if (!configFile.exists()) { 
			
		/* Create a New Configuration File if it does not exist */
			
			try {
				configFile.createNewFile();
			}
			catch (IOException ioe) {
				// Ignore this
			}
			
		/* Load the Configuration File */
			
			try {
				FileInputStream fis = new FileInputStream(configFile);
				config.load(fis);
			}
			catch (FileNotFoundException fnfe) {
				/* IMPORTANT */
				try {
					configFile.createNewFile();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			catch (IOException ioe) {
				/* IMPORTANT */
				ioe.printStackTrace();
			}
			
		/* Set the Properties on the Configuration File 
		   Values are defined/explained above */
			
			config.setProperty(tokenKey, tokenValue);
			config.setProperty(channelKey, channelValue);
			config.setProperty(consoleChannelKey, consoleChannelValue);
			config.setProperty(consoleRoleKey, consoleRoleValue);
			config.setProperty(messageFormatKey, messageFormatValue);
			
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
			}
			catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
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
	
	public List<Role> getConsoleRoles() {
		// TODO getConsoleRoles();
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
	 * Check if any of the fields in the Configuration File are valid.
	 * If any are null, it is not functional.
	 * @return true if none are null, false if any one of the values are null
	**/
	
	public boolean isFunctional() {
		
		/* If any of these return null, it is not Valid. */
		
		/* Configuration Key Checks */
		if (config.getProperty(tokenKey) == null
		 || config.getProperty(channelKey) == null
		 || config.getProperty(consoleChannelKey) == null
		 || config.getProperty(consoleRoleKey) == null
		 || config.getProperty(messageFormatKey) == null) return false;
		
		/* Configuration Value Checks */
		if (config.getProperty(tokenValue) == null
		 || config.getProperty(channelValue) == null
		 || config.getProperty(consoleChannelValue) == null
		 || config.getProperty(consoleRoleValue) == null
		 || config.getProperty(messageFormatValue) == null) return false;
		
		return true; 
		
	}
	
}
