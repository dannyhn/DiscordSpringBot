package com.github.dannyhn.bot.handler;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.data.User;
import com.github.dannyhn.bot.dto.MessageDTO;
import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.sqlite.client.Person;
import com.github.dannyhn.sqlite.client.SqliteClient;
import com.github.dannyhn.sqlite.client.SqliteObjectClient;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.handle.obj.Status.StatusType;

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
