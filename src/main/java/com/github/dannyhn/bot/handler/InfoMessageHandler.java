package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
@Component
public class InfoMessageHandler implements MessageHandler {
	
	@Autowired
	private MessageService messageService;

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = getInfo();
		messageService.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String getInfo() {
		String msg = "A Discord Bot Created and Designed by Danny N. in Collaboration with Tiffany T.\n";
		return msg;
	}



	
}
