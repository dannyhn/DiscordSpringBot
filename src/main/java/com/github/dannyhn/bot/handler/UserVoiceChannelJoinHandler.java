package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.data.User;
import com.github.dannyhn.bot.service.UserService;
import com.github.dannyhn.sqlite.client.Person;
import com.github.dannyhn.sqlite.client.SqliteObjectClient;

import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * @author Danny
 *
 */
@Component
public class UserVoiceChannelJoinHandler {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SqliteObjectClient<User> client;

	public void handleUserVoiceChannelJoinEvent(IVoiceChannel channel, IUser user) {
		String userName = userService.getName(user, channel.getGuild());
		User person = client.read(user.getID(), User.class);
		if (person == null) {
			person = new User();
			person.setId(user.getID());
			person.setName(userName);
		}
		System.out.println("User " + userName + " has joined channel " + channel.getName());
	}
	
}
