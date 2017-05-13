package com.github.ajstri.discordtransfer.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ajstri.discordtransfer.DiscordTransfer;
import com.github.ajstri.discordtransfer.externalplugins.vanish.EssentialsPlugin;

/**
 * The PluginUtils class for Discord Transfer
 * @author AJStri
 * @since May 9th, 2017
**/

public class PluginUtils {
	
	private static Date date = new Date();
	private static SimpleDateFormat format = new SimpleDateFormat("EEE, d. MMM yyyy HH:mm:ss");

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
	
	/**
	 * Return a time stamp.
	 * @return a time stamp
	**/
	
	public static String timeStamp() {
		date.setTime(System.currentTimeMillis());
		return format.format(date);
	}
	
	/**
	 * Retrieve a list of the online players.
	 * @param filterVanished if those who are Vanished should be listed
	 * @return a list of online players
	**/
	
	public static List<Player> getOnlinePlayers (boolean filterVanished) {
		List<Player> onlinePlayers = new ArrayList<>();
		
		try {
			Method onlinePlayersMethod = Server.class.getMethod("getOnlinePlayers");
			
			if(onlinePlayersMethod.getReturnType().equals(Collection.class)) {
				for(Object o : ((Collection<?>) onlinePlayersMethod.invoke(Bukkit.getServer()))) {
					onlinePlayers.add((Player) o);
				}
			}
		}
		catch(Exception e) {
			warning("Getting online players threw an exception");
		}
		
		if(!filterVanished) {
			return onlinePlayers;
		}
		else {
			return onlinePlayers.stream().filter(player -> !isVanished(player)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * Check if a Player is vanished
	 * @param The player to check
	 * @return true if the player is vanished, false if not
	**/
	
	public static boolean isVanished(Player player) {
		return EssentialsPlugin.isVanished(player);
	}
	
}
