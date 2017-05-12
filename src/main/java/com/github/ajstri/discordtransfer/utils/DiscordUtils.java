package com.github.ajstri.discordtransfer.utils;

import java.util.List;

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.exceptions.PermissionException;

/**
 * The DiscordUtils class of DiscordTransfer
 * @author AJStri
 * @since May 9th, 2017
**/

public class DiscordUtils {
	
	/**
	 * Return the input String with escaped Markdown.
	 * @param The message to have Markdown escaped.
	 * @return The message with Markdown escaped.
	**/
	
	public String escapeMarkdown(String message) {
		
		if (message != null) {
			String returnMessage = message.replace("*", "\\*")
										  .replace("_", "\\_")
										  .replace("~", "\\~")
										  .replace("\n", "")
										  .replace("`", "\\`");
			return returnMessage;
		}
		else {
			return "";
		}
	}
	
	/**
	 * Translate emotes into a string.
	 * @param message, the message to be translated
	 * @param g, the Guild from which the message was sent
	 * @return the translated message
	**/
	
	public String translateEmotes(String message, Guild g) {
		if (message != null) {
			
			String returnMessage = "";
			List<Emote> emotes = g.getEmotes();
			
			for (Emote e : emotes) {
				returnMessage = message.replace(":" + e.getName() + ":", e.getAsMention());
			}
			return returnMessage;
		}
		else {
			return "";
		}
	}
	
	/**
	 * Set the Channel Topic
	 * @param channel The Text Channel to update
	 * @param topic The String to set the Topic to
	**/
	
	public void setTextChannelTopic(TextChannel channel, String topic) {
		if (channel == null) {
			PluginUtils.error("Tried setting a Channel Topic to a null channel");
			return;
		}
		else {
			try {
				channel.getManager().setTopic(topic);
			}
			catch (PermissionException pe) {
				PluginUtils.error("Could not set Channel Topic due to lack of permission: " + pe.getPermission());
			}
		}
	}
	
	public void sendGrematMessage(TextChannel channel, int times) {
		if (channel == null) {
			return;
		}
		else {
			for(int i = 0; i < times; i++) {
				channel.sendMessage(":regional_indicator_g:"
						+ ":regional_indicator_r::regional_indicator_e:"
						+ ":regional_indicator_m::regional_indicator_a:" 
						+ ":regional_indicator_t:").queue();
			}
		}
	}
	
}
