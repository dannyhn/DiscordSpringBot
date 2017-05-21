package com.github.dannyhn.bot.message.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.ContextService;
import com.github.dannyhn.bot.service.MessageService;

import sx.blah.discord.handle.obj.IMessage;

@Component
public class ContextMessageHandler implements MessageHandler{
	
	@Autowired
	private ContextService contextService;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void handleMessage(IMessage message) {
		String messageStr = message.getContent().trim();
		String messageToSend = contextService.getContext(messageStr);
		messageService.sendMessage(message.getChannel(), messageToSend, message, false);
	}

}
