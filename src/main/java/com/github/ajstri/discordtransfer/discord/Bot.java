package com.github.ajstri.discordtransfer.discord;

import javax.security.auth.login.LoginException;

import com.github.ajstri.discordtransfer.DiscordTransfer;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

public class Bot {
	
	public JDA jda;
	protected DiscordTransfer discordTransfer;
	
	public Bot (DiscordTransfer discordTransfer) {
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
		JDABuilder jdab = new JDABuilder(AccountType.BOT).setToken(token);
		jdab.addEventListener();
		jda = jdab.buildBlocking();
	}
	
	/**
	 * Shutdown the Discord Bot.
	**/
	
	public void stop() {
		jda.shutdown();
	}
	
}
