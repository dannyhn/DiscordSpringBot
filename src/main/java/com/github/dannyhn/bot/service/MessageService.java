package com.github.dannyhn.bot.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

@Component
public class MessageService {
	
	@Autowired
	private IDiscordClient discordClient;

	public void sendMessage(IChannel channel, String message, IMessage original, boolean delete) {
		if (StringUtils.isEmpty(message)) {
			return;
		}
		try {
			if (delete) {
				original.delete();
			}
			channel.sendMessage(message);
		} catch (Exception e) {
			System.out.println("Error Sending Message: " + message + "\nOriginal: "+ original.getContent()  + " " + e.getMessage());
		}

	}
	
	public void sendMessageToChannel(String channelID, String message) {
		IChannel channel = discordClient.getChannelByID(channelID);
		if (channel != null) {
			try {
				channel.sendMessage(message);
			} catch (MissingPermissionsException | RateLimitException | DiscordException e) {
				e.printStackTrace();
			}
		}
	}
	
}
