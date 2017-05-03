package com.github.dannyhn.bot.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.client.constants.ClientConstants;
import com.github.dannyhn.bot.service.ComplimentService;
import com.github.dannyhn.bot.util.MessageUtil;
import com.github.dannyhn.bot.util.UserUtil;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;


/**
 * @author Tiffany
 *
 */
@Component
public class ComplimentMessageHandler implements MessageHandler {
	
	@Autowired
	private ComplimentService complimentService;

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = getComplimentFromMessage(message);
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	
	/**
	 * Gets compliment based off message
	 * 
	 * @param message
	 * @throws Exception
	 */
	private String getComplimentFromMessage(IMessage message) {
		IGuild currentGuild = message.getGuild();
		List<IUser> usersMentioned = message.getMentions();

		String name;
		String messageToSend = "";
		for (IUser user : usersMentioned) {
			name = UserUtil.getName(user, currentGuild);
			// TODO add delay for spammers
			if (!name.equalsIgnoreCase(ClientConstants.BOTNAME)) {
				messageToSend += complimentService.getCompliment(name) + "\n";
			} else {
				messageToSend += UserUtil.getName(message.getAuthor(), currentGuild) + " likes unicorns\n";
			}
		}
		return messageToSend;
	}



	
}