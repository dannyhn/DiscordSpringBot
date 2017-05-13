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
public class StatusChangeHandler {
	
	@Autowired
	private SqliteObjectClient<User> sqliteClient;

	public void handleStatusChangeEvent(Status status, IUser user) {
		if (status.getType().equals(StatusType.GAME)) {
			String id = user.getID();
			String game = status.getStatusMessage();
			User person = sqliteClient.read(id, User.class);
			if (person == null) {
				person = new User();
				person.setId(id);
				person.setLastGame(game);
			}
			sqliteClient.write(id, person);
			System.out.println("Someone is playing: " + status.getStatusMessage());
			// need to do persistent storage of default channel
		}
	}
	
}
