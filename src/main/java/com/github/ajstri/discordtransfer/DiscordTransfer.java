package com.github.ajstri.discordtransfer;

import javax.security.auth.login.LoginException;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.ajstri.discordtransfer.config.Config;
import com.github.ajstri.discordtransfer.discord.Bot;

import net.dv8tion.jda.core.exceptions.RateLimitedException;

/**
 * The Main Class for DiscordTransfer
 * Yeah, it's not called Main, but that is its function
 * @author AJStri
 * @since May 9th, 2017
**/

public class DiscordTransfer extends JavaPlugin {

	public Config config;
	public Bot bot;
	
	@Override
	public void onEnable() {
		config = new Config(this);
		
		/* Start a new Instance for the Bot. */
		
		if (config.isFunctional()) {
			
			bot = new Bot(this);
			
			/* Add Listeners here */
			
			
			
			try {
				bot.start(config.getToken());
				getLogger().info("Successfully enabled.");
			}
			catch(LoginException e) {
				e.printStackTrace();
			} 
			catch(IllegalArgumentException e) {
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
			getLogger().severe("Configuration File is not functional");
		}
	}
	
	public void onLoad() {
		
	}
	
	public void onDisable() {
		bot.stop();
	}
	
	public static DiscordTransfer getPlugin() {
		return getPlugin(DiscordTransfer.class);
	}
	
}
