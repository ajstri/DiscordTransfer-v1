package com.github.ajstri.discordtransfer.listeners;

import com.github.ajstri.discordtransfer.DiscordTransfer;
import com.github.ajstri.discordtransfer.discord.Bot;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class InGameChatListener extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		
		if (e.getAuthor() == null || e.getAuthor().getId() == null) {
			return;  
		}
		else if (e.getAuthor().getId() != Bot.id) {
			if (e.getChannel().getId() == DiscordTransfer.config.getMainChannel().getId()) {
				
			}
		}
		
	}
	
}
