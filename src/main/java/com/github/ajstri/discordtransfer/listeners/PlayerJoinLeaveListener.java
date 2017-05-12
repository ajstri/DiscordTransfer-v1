package com.github.ajstri.discordtransfer.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeaveListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		// TODO onPlayerJoin();
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		// TODO onPlayerLeave();
	}
}
