package com.github.ajstri.discordtransfer;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.ajstri.discordtransfer.config.Config;
import com.github.ajstri.discordtransfer.discord.Bot;
import com.github.ajstri.discordtransfer.listeners.*;
import com.github.ajstri.discordtransfer.utils.PluginUtils;

import net.dv8tion.jda.core.exceptions.RateLimitedException;

/**
 * The Main Class for DiscordTransfer
 * Yeah, it's not called Main, but that is its function
 * @author AJStri
 * @since May 9th, 2017
**/

public class DiscordTransfer extends JavaPlugin {

	public static Config config;
	public Bot bot;
	
	@Override
	public void onEnable() {
		config = new Config(this);
		
		/* Start a new Instance of the Bot. */
		
		if (config.isFunctional()) {
			
			bot = new Bot(this);
			
			/* We don't need more than one instance. 
			 * If it gets reloaded, or something happens that we 
			 * have the instance before startup, we'll shut it down. */
			if (bot.jda != null) {
				try {
					bot.stop();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				bot.start(config.getToken());
				getLogger().info("Successfully enabled.");
			}
			catch(LoginException e) {
				PluginUtils.error("Failed to Connect to Discord");
				e.printStackTrace();
			} 
			catch(IllegalArgumentException e) {
				PluginUtils.error("Arguments are not valid");
				e.printStackTrace();
			} 
			catch(InterruptedException e) {
				e.printStackTrace();
			} 
			catch(RateLimitedException e) {
				e.printStackTrace();
			}
		}
		else {
			PluginUtils.error("Configuration File is not functional");
		}
		
		// Set game status
		bot.setGameStatus(config.getGameStatus());
		
		/* What happens if the bot isn't in any guilds? */
		if(!bot.isInGuild()) {
			PluginUtils.error("Bot is not in any servers.");
		}
			
		/* Add Listeners here */
		
		// Discord Listeners
		bot.jda.addEventListener(new ConsoleChannelListener());
		bot.jda.addEventListener(new MainChannelListener());
		
		// Minecraft Listeners
		
		getServer().getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
		// getServer().getPluginManager().registerEvents(new ClassName, this);
		

	}
	
	public void onDisable() {
		// TODO send message "Server offline"
		bot.stop();
	}
	
	public static DiscordTransfer getPlugin() {
		return getPlugin(DiscordTransfer.class);
	}
	
}
