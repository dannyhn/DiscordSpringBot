package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.UserService;
import com.github.dannyhn.bot.util.MessageUtil;
import com.github.dannyhn.game.machikoro.Board;
import com.github.dannyhn.game.machikoro.Card;
import com.github.dannyhn.game.machikoro.Player;

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
public class PlayerMessageHandler implements MessageHandler {
	
	@Autowired
	private UserService userService;

	@Override
	public void handleMessage(IMessage message) {
		IGuild guild = message.getGuild();
		String messageToSend = "";
		for (IUser user : message.getMentions()) {
			messageToSend += getInfo(user, guild);
		}
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String getInfo(IUser user, IGuild guild) {
		Player player = Board.getInstance().getPlayer(user.getID());
		if (player == null) {
			return "Could not find Player: " + userService.getName(user, guild) + "\n";
		}
		String msg = "";
		msg += "Name: " + player.getName() + "\n";
		msg += "Money: " + player.getMoney() + "\n";
		for (Card card : player.getCards()) {
			msg += "Card: " + card.getName() + " x" + card.getQuantity() + "\n";
		}
		return msg;
	}



	
}
