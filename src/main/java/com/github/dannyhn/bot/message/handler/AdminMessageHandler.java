package com.github.dannyhn.bot.message.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.data.User;
import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.sqlite.client.SqliteObjectClient;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
@Component
public class AdminMessageHandler implements MessageHandler {
	
	@Autowired
	private IDiscordClient client;
	
	@Autowired
	private SqliteObjectClient<User> sqliteClient;
	
	@Override
	public void handleMessage(IMessage message) {
		try {
			saveProperty(message);
			message.delete();
		} catch (DiscordException | MissingPermissionsException | RateLimitException e) {
			e.printStackTrace();
		}

	}
	

	private void saveProperty(IMessage message) throws DiscordException {
		IUser author = message.getAuthor();
		if (client.getApplicationOwner().getID().equals(author.getID())) {
			List<IUser> users = message.getMentions();
			if (users.size() == 1) {
				String[] words = message.getContent().split(" ");
				if (words.length == 4) {
					String propertyName = words[2];
					String propertyValue = words[3];
					User user = sqliteClient.read(users.get(0).getID(), User.class);
					if (user == null) {
						user = new User();
						user.setId(users.get(0).getID());
					}
					user.setProperty(propertyName, propertyValue);
					sqliteClient.write(user.getId(), user);
				}
			}
		}
	}



	
}
