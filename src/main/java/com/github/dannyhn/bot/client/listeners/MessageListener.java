package com.github.dannyhn.bot.client.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.handler.MessageReceivedHandler;
import com.github.dannyhn.bot.handler.MessageSendHandler;
import com.github.dannyhn.bot.handler.StatusChangeHandler;
import com.github.dannyhn.bot.handler.TypingEventHandler;
import com.github.dannyhn.bot.handler.UserUpdateHandler;
import com.github.dannyhn.bot.handler.UserVoiceChannelJoinHandler;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.MessageSendEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.StatusChangeEvent;
import sx.blah.discord.handle.impl.events.TypingEvent;
import sx.blah.discord.handle.impl.events.UserUpdateEvent;
import sx.blah.discord.handle.impl.events.UserVoiceChannelJoinEvent;

/**
 * @author Danny
 *
 */
@Component
public class MessageListener {
	
	@Autowired
	private StatusChangeHandler statusChangeHandler;
	
	@Autowired
	private UserVoiceChannelJoinHandler userVoiceChannelJoinHandler;
	
	@Autowired
	private TypingEventHandler typingEventHandler;
	
	@Autowired
	private MessageReceivedHandler messageReceivedHandler;
	
	@Autowired
	private UserUpdateHandler userUpdateHandler;
	
	@Autowired
	private MessageSendHandler messageSendHandler;

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
		statusChangeHandler.handleStatusChangeEvent(event.getNewStatus(), event.getUser());
	}
	
	@EventSubscriber
	public void onUserUpdateEvent(UserUpdateEvent event) {
		userUpdateHandler.handleUserUpdateEvent(event.getNewUser());
	}
	
	@EventSubscriber
	public void onMessageSendEvent(MessageSendEvent event) {
		messageSendHandler.handleMessageSendEvent(event.getMessage());
	}

	/**
	 * Triggers on message received event
	 * 
	 * @param event
	 * @throws Exception
	 */
	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		messageReceivedHandler.handleMessageReceivedEvent(event.getMessage());
	}
	
	@EventSubscriber
	public void onUserVoiceChannelJoinEvent(UserVoiceChannelJoinEvent event) {
		userVoiceChannelJoinHandler.handleUserVoiceChannelJoinEvent(event.getChannel(), event.getUser());
		
	}
	
	@EventSubscriber
	public void onTypingEvent(TypingEvent event) {
		typingEventHandler.handleTypingEvent(event.getChannel(), event.getUser());
	}
	
}
