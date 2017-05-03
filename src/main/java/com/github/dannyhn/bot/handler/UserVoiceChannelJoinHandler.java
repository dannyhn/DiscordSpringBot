package com.github.dannyhn.bot.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.UserService;

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

	public void handleUserVoiceChannelJoinEvent(IVoiceChannel channel, IUser user) {
		String userName = userService.getName(user, channel.getGuild());
		System.out.println("User " + userName + " has joined channel " + channel.getName());
	}
	
}
