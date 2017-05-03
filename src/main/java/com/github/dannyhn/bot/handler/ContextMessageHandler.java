package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.ContextService;
import com.github.dannyhn.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.IMessage;

@Component
public class ContextMessageHandler implements MessageHandler{
	
	@Autowired
	private ContextService contextService;
	
	@Override
	public void handleMessage(IMessage message) {
		String messageStr = message.getContent().trim();
		String messageToSend = contextService.getContext(messageStr);
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, false);
	}

}
