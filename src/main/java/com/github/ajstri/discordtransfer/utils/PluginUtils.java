package com.github.ajstri.discordtransfer.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ajstri.discordtransfer.DiscordTransfer;

/**
 * The PluginUtils class for Discord Transfer
 * @author AJStri
 * @since May 9th, 2017
**/

public class PluginUtils {

	/**
	 * Retrieve a JavaPlugin by the name pluginName
	 * @param pluginName the plugin to retrieve
	 * @return The Plugin with the name as defined with pluginName
	**/
	
	public static JavaPlugin getPlugin (String pluginName) {
		for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
			if (plugin.getName().toLowerCase().startsWith(pluginName.toLowerCase())) {
				return (JavaPlugin) plugin;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param message
	**/
	
	public static void debug(String message) {
		DiscordTransfer.getPlugin().getLogger().info("[DEBUG] " + message);
	}
	
	/**
	 * Print an info log.
	 * @param message to send to console
	**/
	
	public static void info(String message) {
		DiscordTransfer.getPlugin().getLogger().info(message);
	}
	
	/**
	 * Print a warning log.
	 * @param message to send to console
	**/
	
	public static void warning(String message) {
		DiscordTransfer.getPlugin().getLogger().warning(message);
	}
	
	/**
	 * Print an error log.
	 * @param message to send to console
	**/
	
	public static void error(String message) {
		DiscordTransfer.getPlugin().getLogger().severe(message);
	}
	
}
