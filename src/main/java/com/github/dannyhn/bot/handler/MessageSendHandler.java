package com.github.dannyhn.bot.handler;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.dto.MessageDTO;
import sx.blah.discord.handle.obj.IMessage;

/**
 * @author Danny
 *
 */
@Component
public class MessageSendHandler {
	
	@Autowired
	private Queue<MessageDTO> messageList;
	

	@CacheEvict(value = {"messagelist"}, allEntries = true)
	public void handleMessageSendEvent(IMessage message) {
		messageList.add(new MessageDTO(message));
	}
	
}
