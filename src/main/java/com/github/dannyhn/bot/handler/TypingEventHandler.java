package com.github.dannyhn.bot.handler;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.bot.service.RandomWordService;
import com.github.dannyhn.bot.service.UserService;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Danny
 *
 */
@Component
public class TypingEventHandler {
	
	@Autowired
	private RandomWordService randomWordService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MessageService messageService;
	
	private static long time = 1;

	private static final long FIFTEENSECONDS = 15000;
	public void handleTypingEvent(IChannel channel, IUser user) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - time > FIFTEENSECONDS) {
			String insult = typingInsult(userService.getName(user, channel.getGuild()));
			time = currentTime;
			//messageService.sendMessage(channel, insult, null, false);
		}
	}
	
	private String typingInsult(String userName) {
		Random random = new Random();
		
		int randInt = random.nextInt(3);
		switch(randInt) {
		case 0:
			return userName + " types slower than a " + randomWordService.randomAnimal();
		case 1:
			return "does " + userName + " know that you can type with two hands";
		case 2:
			return userName + " types so god damn much";
		default:
			return userName + " sucks cows";
		}
	}
	
}
