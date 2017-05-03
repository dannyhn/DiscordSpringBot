package com.github.dannyhn.bot.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

@Component
public class MessageService {

	public void sendMessage(IChannel channel, String message, IMessage original, boolean delete) {
		if (StringUtils.isEmpty(message)) {
			return;
		}
		try {
			channel.sendMessage(message);
			if (delete) {
				original.delete();
			}
		} catch (Exception e) {
			System.out.println("Error Sending Message: " + message + "\nOriginal: "+ original.getContent()  + " " + e.getMessage());
		}

	}
	
}
