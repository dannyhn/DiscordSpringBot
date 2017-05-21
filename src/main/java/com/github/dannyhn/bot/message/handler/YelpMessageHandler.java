package com.github.dannyhn.bot.message.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.bot.service.YelpService;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Yelp
 * 
 * @author Danny
 *
 */
@Component
public class YelpMessageHandler implements MessageHandler{
	
	@Autowired
	private YelpService yelpService;
	
	@Autowired
	private MessageService messageService;

	@Override
	public void handleMessage(IMessage message) {
		if (message.getContent().toLowerCase().contains(".yelplist")) {
			handleYelpListMessage(message);
		} else {
			handleYelpMessage(message);
		}
	}
	
	/**
	 * handles .yelp message
	 * 
	 * @param message
	 */
	private void handleYelpMessage(IMessage message) {
		String zipcode = message.getContent().replaceAll(".yelp", "");
		zipcode = zipcode.trim();
		String restaurant = yelpService.getYelpInfoFromZipCode(zipcode);
		messageService.sendMessage(message.getChannel(), restaurant, message, false);
	}
	
	/**
	 * handles .yelplist message
	 * 
	 * @param message
	 */
	private void handleYelpListMessage(IMessage message) {
		String zipcode = message.getContent().replaceAll(".yelplist", "");
		zipcode = zipcode.trim();
		String restaurant = yelpService.getYelpListInfoFromZipCode(zipcode);
		messageService.sendMessage(message.getChannel(), restaurant, message, false);
	}

}
