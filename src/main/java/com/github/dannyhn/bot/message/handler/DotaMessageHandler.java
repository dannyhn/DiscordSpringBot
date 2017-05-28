package com.github.dannyhn.bot.message.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.data.User;
import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.sqlite.client.SqliteObjectClient;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
@Component
public class DotaMessageHandler implements MessageHandler {

	@Autowired
	private MessageService messageService;

	@Autowired
	private SqliteObjectClient<User> sqliteClient;

	@Override
	public void handleMessage(IMessage message) {
		List<IUser> mentions = message.getMentions();
		if (mentions.isEmpty()) {
			handleMessageForUser(message.getAuthor(), message);
		} else {
			handleMessageForUserList(mentions, message);
		}
	}

	private void handleMessageForUser(IUser user, IMessage message) {
		User userFromDB = sqliteClient.read(user.getID(), User.class);
		if (userFromDB == null || StringUtils.isBlank(userFromDB.getDotaUrl())) {
			messageService.sendMessage(message.getChannel(), user.getName() + " is not important enough", message,
					true);
		} else {
			messageService.sendMessage(message.getChannel(), userFromDB.getDotaUrl(), message, true);
		}
	}
	
	private void handleMessageForUserList(List<IUser> users, IMessage message) {
		String messageToSend = "";
		for (IUser user : users) {
			User userFromDB = sqliteClient.read(user.getID(), User.class);
			if (userFromDB == null || StringUtils.isBlank(userFromDB.getDotaUrl())) {
				messageToSend += user.getName() + " is not important enough\n";
			} else {
				messageToSend += userFromDB.getDotaUrl() + "\n";
			}
		}
		messageService.sendMessage(message.getChannel(), messageToSend, message, true);

	}
}
