package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.UserService;
import com.github.dannyhn.bot.util.MessageUtil;
import com.github.dannyhn.game.machikoro.Board;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
@Component
public class ReadyMessageHandler implements MessageHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = readyUp(message.getAuthor(), message.getGuild());
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String readyUp(IUser user, IGuild guild) {
		Board.getInstance().addPlayer(user.getID(), userService.getName(user, guild));
		String msg = userService.getName(user, guild) + " is Ready!";
		return msg;
	}



	
}
