package com.github.dannyhn.bot.handler;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.dto.MessageDTO;
import com.github.dannyhn.bot.handler.factory.MessageHandlerFactory;
import com.github.dannyhn.bot.message.handler.ContextMessageHandler;
import com.github.dannyhn.bot.message.handler.MessageHandler;
import com.github.dannyhn.bot.service.ContextService;
import com.github.dannyhn.bot.service.RateLimitingService;
import sx.blah.discord.handle.obj.IMessage;

/**
 * @author Danny
 *
 */
@Component
public class MessageReceivedHandler {
	
	
	@Autowired
	private MessageHandlerFactory messageHandlerFactory;
	
	@Autowired
	private ContextService contextService;
	
	@Autowired
	private RateLimitingService rateLimitingService;
	
	@Autowired
	private Queue<MessageDTO> messageList;

	@CacheEvict(value = {"messagelist"}, allEntries = true)
	public void handleMessageReceivedEvent(IMessage message) {
		messageList.add(new MessageDTO(message));
		System.out.println( "Guild: " + message.getGuild() + " "  + message.getAuthor().getName() + " : " + message);
		MessageHandler handler = messageHandlerFactory.getMessageHandler(message);
		if (isValidCommand(handler, message) && canMakeRequest(message)) {
			handler.handleMessage(message);
		}
	}
	
	
	private boolean isValidCommand(MessageHandler handler, IMessage message) {
		if (handler instanceof ContextMessageHandler)  {
			return contextService.getContext(message.getContent()) != null;
		} else {
			return true;
		}
	}
	
	private boolean canMakeRequest(IMessage message) {
		return rateLimitingService.canMakeRequest(message.getAuthor().getID());
	}
	
	
	
}
