package com.github.ajstri.discordtransfer.utils;

public class DiscordUtils {
	
	/**
	 *  Return the input String with escaped Markdown.
	 *  @param The message to have Markdown escaped.
	 *  @return The message with Markdown escaped.
	**/
	
	public String escapeMarkdown(String message) {
		
		if (message == null) return ""; // If there's no message, why try?
		else {
			String returnMessage = message.replace("*", "\\*")
										  .replace("_", "\\_")
										  .replace("~", "\\~")
										  .replaceAll("\n", "");
			return returnMessage;
		}
	}
	
}
