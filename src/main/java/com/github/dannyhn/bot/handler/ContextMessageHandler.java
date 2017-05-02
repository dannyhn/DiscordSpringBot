package com.github.dannyhn.bot.handler;

import com.github.dannyhn.bot.service.ContextService;
import com.github.dannyhn.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.IMessage;

public class ContextMessageHandler implements MessageHandler{

	@Override
	public void handleMessage(IMessage message) {
		String messageStr = message.getContent().trim();
		ContextService contextService = ContextService.getInstance();
		String messageToSend = contextService.getContext(messageStr);
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, false);
	}

}
