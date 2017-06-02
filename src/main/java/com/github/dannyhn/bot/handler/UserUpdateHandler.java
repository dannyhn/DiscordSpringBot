package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.data.User;
import com.github.dannyhn.bot.service.LogService;
import com.github.dannyhn.sqlite.client.SqliteObjectClient;

import sx.blah.discord.handle.obj.IUser;

/**
 * @author Danny
 *
 */
@Component
public class UserUpdateHandler {
	
	@Autowired
	private SqliteObjectClient<User> sqliteClient;
	
	@Autowired
	private LogService log;

	public void handleUserUpdateEvent(IUser user) {
		String id = user.getID();
		User dbUser = sqliteClient.read(id, User.class);
		if (dbUser == null) {
			dbUser = new User();
			dbUser.setId(id);
		}
		dbUser.setAvatarUrl(user.getAvatarURL());
		log.log("User Status Changed: " + user.getName());
		//System.out.println("User Status Changed: " + user.getName());
	}
	
}
