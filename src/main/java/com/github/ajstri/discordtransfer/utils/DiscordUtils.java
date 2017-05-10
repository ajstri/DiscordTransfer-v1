package com.github.ajstri.discordtransfer.utils;

import java.util.List;

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.Guild;

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
	
}
