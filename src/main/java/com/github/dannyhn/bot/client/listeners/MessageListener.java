package com.github.dannyhn.bot.client.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.handler.ContextMessageHandler;
import com.github.dannyhn.bot.handler.MessageHandler;
import com.github.dannyhn.bot.handler.StatusChangeHandler;
import com.github.dannyhn.bot.handler.TypingEventHandler;
import com.github.dannyhn.bot.handler.UserVoiceChannelJoinHandler;
import com.github.dannyhn.bot.handler.factory.MessageHandlerFactory;
import com.github.dannyhn.bot.service.ContextService;
import com.github.dannyhn.bot.service.RandomWordService;
import com.github.dannyhn.bot.service.RateLimitingService;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.StatusChangeEvent;
import sx.blah.discord.handle.impl.events.TypingEvent;
import sx.blah.discord.handle.impl.events.UserVoiceChannelJoinEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Status;

/**
 * @author Danny
 *
 */
@Component
public class MessageListener {
	
	@Autowired
	private MessageHandlerFactory messageHandlerFactory;
	
	@Autowired
	private StatusChangeHandler statusChangeHandler;
	
	@Autowired
	private UserVoiceChannelJoinHandler userVoiceChannelJoinHandler;
	
	@Autowired
	private TypingEventHandler typingEventHandler;
	
	@Autowired
	private RateLimitingService rateLimitingService;
	
	@Autowired
	private ContextService contextService;

	/**
	 * Triggers on bot startup
	 * 
	 * @param event
	 */
	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		//RandomWordService.getInstance();
	}
	
	@EventSubscriber
	public void onStatusChangeEvent(StatusChangeEvent event) {
		statusChangeHandler.handleStatusChangeEvent(event.getNewStatus());
	}

	/**
	 * Triggers on message received event
	 * 
	 * @param event
	 * @throws Exception
	 */
	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		MessageHandler handler = messageHandlerFactory.getMessageHandler(message);
		if (isValidCommand(handler, message) && canMakeRequest(message)) {
			handler.handleMessage(message);
		}

	}
	
	@EventSubscriber
	public void onUserVoiceChannelJoinEvent(UserVoiceChannelJoinEvent event) {
		userVoiceChannelJoinHandler.handleUserVoiceChannelJoinEvent(event.getChannel(), event.getUser());
		
	}
	
	@EventSubscriber
	public void onTypingEvent(TypingEvent event) {
		typingEventHandler.handleTypingEvent(event.getChannel(), event.getUser());
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
