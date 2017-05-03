package com.github.dannyhn.bot.handler;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.bot.service.UserService;
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
public class RollMessageHandler implements MessageHandler {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = readyUp(message.getAuthor(), message.getGuild());
		messageService.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String readyUp(IUser user, IGuild guild) {
		String msg = "";
		Board board = Board.getInstance();
		if (board.canPlayerRoll(user.getID())) {
			msg += board.doPlayerRoll(user.getID());
		} else {
			msg += userService.getName(user, guild) + " cant Roll";
		}
		

		return msg;
	}



	
}
