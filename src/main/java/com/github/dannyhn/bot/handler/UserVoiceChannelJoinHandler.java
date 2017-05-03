package com.github.dannyhn.bot.handler;

import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.util.UserUtil;

import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * @author Danny
 *
 */
@Component
public class UserVoiceChannelJoinHandler {

	public void handleUserVoiceChannelJoinEvent(IVoiceChannel channel, IUser user) {
		String userName = UserUtil.getName(user, channel.getGuild());
		System.out.println("User " + userName + " has joined channel " + channel.getName());
	}
	
}
