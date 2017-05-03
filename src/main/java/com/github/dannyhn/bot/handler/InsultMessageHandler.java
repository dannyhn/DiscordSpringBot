package com.github.dannyhn.bot.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.client.constants.ClientConstants;
import com.github.dannyhn.bot.service.InsultService;
import com.github.dannyhn.bot.util.MessageUtil;
import com.github.dannyhn.bot.util.UserUtil;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Message Handler for Insults
 * 
 * @author Danny
 *
 */
@Component
public class InsultMessageHandler implements MessageHandler {
	
	@Autowired
	private InsultService insultService;

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = getInsultFromMessage(message);
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	
	/**
	 * Gets insult based off message
	 * 
	 * @param message
	 * @throws Exception
	 */
	private String getInsultFromMessage(IMessage message) {
		IGuild currentGuild = message.getGuild();
		List<IUser> usersMentioned = message.getMentions();

		String name;
		String messageToSend = "";
		for (IUser user : usersMentioned) {
			name = UserUtil.getName(user, currentGuild);
			// TODO add delay for spammers
			if (!name.equalsIgnoreCase(ClientConstants.BOTNAME)) {
				messageToSend += insultService.getInsult(name) + "\n";
			} else {
				messageToSend += UserUtil.getName(message.getAuthor(), currentGuild) + " sucks cows\n";
			}
		}
		return messageToSend;
	}



	
}
