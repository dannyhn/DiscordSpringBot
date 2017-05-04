package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;
import com.github.dannyhn.sqlite.client.SqliteClient;

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
	private SqliteClient sqliteClient;

	public void handleStatusChangeEvent(Status status, IUser user) {
		if (status.getType().equals(StatusType.GAME)) {
			String id = user.getID();
			String game = status.getStatusMessage();
			sqliteClient.write(id, game);
			System.out.println("Someone is playing: " + status.getStatusMessage());
			// need to do persistent storage of default channel
		}
	}
	
}
