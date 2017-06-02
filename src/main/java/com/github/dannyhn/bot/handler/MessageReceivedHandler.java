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
import com.github.dannyhn.bot.service.LogService;
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
	
	@Autowired
	private LogService log;

	@CacheEvict(value = {"messagelist"}, allEntries = true)
	public void handleMessageReceivedEvent(IMessage message) {
		if (message.getGuild() != null && message.getChannel().getID().equals("319967798724132872")) { //websocket channel
			return;
		}
		messageList.add(new MessageDTO(message));
		if (message.getGuild() == null) {
			log.log("DM Received From " + message.getAuthor().getName());
			//System.out.println( "DM: "  + message.getAuthor().getName() + " : " + message);
		} else {
			log.log(message.getGuild().getName() + ": "  + message.getAuthor().getName() + " : " + message);
			//System.out.println( message.getGuild().getName() + ": "  + message.getAuthor().getName() + " : " + message);
		}
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
