package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Steven
 * 
 * @author Tiffany
 *
 */
@Component
public class StevenMessageHandler implements MessageHandler {

	@Autowired
	private MessageService messageService;
	
	@Override
	public void handleMessage(IMessage message) {
		IChannel currentChannel = message.getChannel();

		String messageToSend = "Good Clothes. Visit: http://www.milleniumbrand.com/";

		messageService.sendMessage(currentChannel, messageToSend, message, true);

		// "Good Clothes. Visit: http://www.milleniumbrand.com/";
	}

}
