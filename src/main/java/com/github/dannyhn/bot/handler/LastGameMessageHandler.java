package com.github.dannyhn.bot.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.sqlite.client.SqliteClient;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Message Handler for Last Game
 * 
 * @author Danny
 *
 */
@Component
public class LastGameMessageHandler implements MessageHandler {

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private SqliteClient sqliteClient;
	
	@Override
	public void handleMessage(IMessage message) {
		List<IUser> users = message.getMentions();

		if (users.size() == 1) {
			String messageToSend = getLastGame(users.get(0).getID());
			messageService.sendMessage(message.getChannel(), messageToSend, message, true);
		}

	}
	
	private String getLastGame(String id) {
		String game = sqliteClient.read(id);
		if ("error".equals(game)) {
			return "Information Not Found";
		} else {
			return "Last Game Played: " + game;
		}
	}
	
}
