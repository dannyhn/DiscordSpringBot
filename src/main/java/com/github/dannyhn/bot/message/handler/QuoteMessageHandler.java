package com.github.dannyhn.bot.message.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.bot.service.RandomWordService;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Quotes
 * 
 * @author Danny
 *
 */
@Component
public class QuoteMessageHandler implements MessageHandler {
	
	@Autowired
	private RandomWordService randomWordService;
	
	@Autowired
	private MessageService messageService;

	@Override
	public void handleMessage(IMessage message) {
		IChannel currentChannel = message.getChannel();
		String quote = randomWordService.randomQuote();
		messageService.sendMessage(currentChannel, quote, message, true);

	}

}
