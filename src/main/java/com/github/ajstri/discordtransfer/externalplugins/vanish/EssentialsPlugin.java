package com.github.ajstri.discordtransfer.externalplugins.vanish;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.github.ajstri.discordtransfer.utils.PluginUtils;

public class EssentialsPlugin {

	/**
	 * Check if a Player is vanished by Essentials
	 * @param the player to check.
	 * @return true if the player is vanished, false if the player is not
	**/
	
	public static boolean isVanished(Player player) {
		try {
			Plugin essentials = PluginUtils.getPlugin("Essentials");
			Method getUser = essentials.getClass().getMethod("getUser", String.class);
			Object essentialsPlayer = getUser.invoke(essentials, player.getName());
			
			if(essentialsPlayer != null) {
				Method isVanished = essentialsPlayer.getClass().getMethod("isVanished");
				return (boolean) isVanished.invoke(essentialsPlayer);
			}
			
		}
		catch (Exception e) {
			PluginUtils.warning("Checking if a player is Vanished (isVanished) threw an Exception");
		}
		
		return false;
	}
	
}
