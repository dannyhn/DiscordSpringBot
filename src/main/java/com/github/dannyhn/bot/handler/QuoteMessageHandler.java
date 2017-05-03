package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.RandomWordService;
import com.github.dannyhn.bot.util.MessageUtil;

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

	@Override
	public void handleMessage(IMessage message) {
		IChannel currentChannel = message.getChannel();
		String quote = randomWordService.randomQuote();
		MessageUtil.sendMessage(currentChannel, quote, message, true);

	}

}
