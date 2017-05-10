package com.github.ajstri.discordtransfer.listeners;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class InGameChatListener extends ListenerAdapter {

	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		
		if (e.getAuthor() == null || e.getAuthor().getId() == null) {
			return;  
		}
		
	}
	
}
