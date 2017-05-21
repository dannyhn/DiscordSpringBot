package com.github.dannyhn.bot.message.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.data.User;
import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.sqlite.client.SqliteClient;
import com.github.dannyhn.sqlite.client.SqliteObjectClient;

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
	private SqliteObjectClient<User> sqliteClient;
	
	
	@Override
	public void handleMessage(IMessage message) {
		List<IUser> users = message.getMentions();

		if (users.size() == 1) {
			String messageToSend = getLastGame(users.get(0).getID());
			messageService.sendMessage(message.getChannel(), messageToSend, message, true);
		}

	}
	
	private String getLastGame(String id) {
		User user = sqliteClient.read(id, User.class);
		if (user == null) {
			return "Information Not Found";
		} else {
			return "Last Game Played: " + user.getLastGame();
		}
	}
	
}
