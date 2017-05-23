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
		IUser user = message.getAuthor();
		User userFromDB = sqliteClient.read(user.getID(), User.class);
		if (userFromDB == null || StringUtils.isBlank(userFromDB.getDotaUrl()))
		{
			messageService.sendMessage(message.getChannel(),"You are not important enough", message, true);
		}
		else
		{
			messageService.sendMessage(message.getChannel(),userFromDB.getDotaUrl(), message, true);
		}
		/*
		if ("209171660224462859".equalsIgnoreCase(user.getID()))
		{
			messageService.sendMessage(message.getChannel(),"https://pubg.me/MasterTactioner", message, true);
		}
		else if ("219038305801601024".equalsIgnoreCase(user.getID()))
		{
			messageService.sendMessage(message.getChannel(),"https://pubg.me/yofu-kashi", message, true);
		}
		else if ("159729251707387904".equalsIgnoreCase(user.getID()))
		{
			messageService.sendMessage(message.getChannel(),"https://pubg.me/crytax", message, true);
		}
		else if("94917039277223936".equalsIgnoreCase(user.getID()))
		{
			messageService.sendMessage(message.getChannel(),"https://pubg.me/senpai-_-", message, true);
		}
		User newUser = sqliteClient.read(user.getID(), User.class);
		*/
	}	
}
