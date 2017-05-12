package com.github.ajstri.discordtransfer.discord;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.github.ajstri.discordtransfer.DiscordTransfer;
import com.github.ajstri.discordtransfer.utils.PluginUtils;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

/**
 * The Bot Class of Discord Transfer
 * @author AJStri
 * @since May 9th, 2017
**/

public class Bot {
	
	public static String id;
	public JDA jda;
	
	protected DiscordTransfer discordTransfer;
	
	public Bot(DiscordTransfer discordTransfer) {
		this.discordTransfer = discordTransfer;
	}

	/** 
	 * Start the Discord Bot.
	 * @param The Discord Bot's token
	 * @throws LoginException
	 * @throws IllegalArgumentException
	 * @throws InterruptedException
	 * @throws RateLimitedException
	**/
	
	public void start(String token) throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {
		JDABuilder jdab = new JDABuilder(AccountType.BOT)
										.setToken(token)
										.setAutoReconnect(true);
		jdab.addEventListener();
		jda = jdab.buildBlocking();
	
		id = jda.getSelfUser().getId();
	}
	
	/**
	 * Shutdown the Discord Bot.
	**/
	
	public void stop() {
		if (jda != null) {
			jda.shutdown();
		}
	}
	
	/**
	 * Get the JDA Instance of the Bot.
	 * @return the JDA Instance
	**/
	
	public JDA getJDA() {
		return DiscordTransfer.getPlugin().bot.getJDA();
	}
	
	/**
	 * Get the ID of the Discord Bot
	 * @return the ID of the Discord Bot
	**/
	
	public String getBotID() {
		return jda.getSelfUser().getId();
	}
	
	/**
	 * Return a single guild the bot is in.
	 * @return
	**/
	
	public Guild getGuild() {
		return jda.getGuilds().get(0);
	}
	
	/**
	 * Get all the guilds the Discord bot is in.
	 * @return
	**/
	
	public List<Guild> getGuilds() {
		return jda.getGuilds();
	}
	
	public void setGameStatus(String gameStatus) {
		if (getJDA() == null) {
			PluginUtils.debug("Tried to set Game of Bot with a null JDA.");
		}
		else if (gameStatus == null) {
			PluginUtils.debug("Tried to set Game of Bot to a null token.");
		}
		else {
			getJDA().getPresence().setGame(Game.of(gameStatus));
		}
	}
	
	public boolean isInGuild() {
		if(getJDA().getGuilds().size() == 0) {
			return false;
		}
		else {
			return true; // TODO don't make stupid typos
		}
	}
	
}
