package com.github.dannyhn.bot.handler;

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
public class QuoteMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		IChannel currentChannel = message.getChannel();
		String quote = RandomWordService.getInstance().randomQuote();
		MessageUtil.sendMessage(currentChannel, quote, message, true);

	}

}
