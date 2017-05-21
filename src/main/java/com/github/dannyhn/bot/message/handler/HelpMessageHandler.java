package com.github.dannyhn.bot.message.handler;

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
public class HelpMessageHandler implements MessageHandler {

	@Autowired
	private MessageService messageService;
	
	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = getHelp();
		messageService.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String getHelp() {
		String helpMsg = "";
		helpMsg += " - .steven to display url to steven's website\n";
		helpMsg += " - .i @User to generate an insult to that user\n";
		helpMsg += " - .c @User to generate a compliment for a user\n";
		helpMsg += " - .yelp 'Zipcode' to find a restaurant for a given zipcode\n";
		helpMsg += " - .yelplist 'Zipcode' to list restaurants for a given zipcode\n";
		return helpMsg;
	}



	
}
