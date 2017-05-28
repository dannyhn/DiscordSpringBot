package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.data.User;
import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.sqlite.client.Person;
import com.github.dannyhn.sqlite.client.SqliteClient;
import com.github.dannyhn.sqlite.client.SqliteObjectClient;

import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.handle.obj.Status.StatusType;

/**
 * @author Danny
 *
 */
@Component
public class UserUpdateHandler {
	
	@Autowired
	private SqliteObjectClient<User> sqliteClient;

	public void handleUserUpdateEvent(IUser user) {
		String id = user.getID();
		User dbUser = sqliteClient.read(id, User.class);
		if (dbUser == null) {
			dbUser = new User();
			dbUser.setId(id);
		}
		dbUser.setAvatarUrl(user.getAvatarURL());
		System.out.println("User: " + user.getName() + " has new Avatar: " + user.getAvatarURL());
	}
	
}