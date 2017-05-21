package com.github.dannyhn.bot.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Danny
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO {
	
	private String guild;
	private String guildId;
	private String user;
	private String userId;
	private String channel;
	private String channelId;
	private long timestamp;
	private String message;
	
	@JsonCreator
	public MessageDTO(@JsonProperty("guild") String guild, 
			@JsonProperty("guildId") String guildId,
			@JsonProperty("user") String user, 
			@JsonProperty("userId") String userId, 
			@JsonProperty("channel") String channel, 
			@JsonProperty("channelId") String channelId, 
			@JsonProperty("timestamp") long timestamp, 
			@JsonProperty("message") String message) {
		this.guild = guild;
		this.guildId = guildId;
		this.user = user;
		this.userId = userId;
		this.channel = channel;
		this.channelId = channelId;
		this.timestamp = timestamp;
		this.message = message;
	}
	
	public MessageDTO(IMessage message) {
		IGuild messageGuild = message.getGuild();
		if (messageGuild != null) {
			this.guild = messageGuild.getName();
			this.guildId = messageGuild.getID();
		}
		IUser messageUser = message.getAuthor();
		if (messageUser != null) {
			this.user = messageUser.getName();
			this.userId = messageUser.getID();
		}
		IChannel messageChannel = message.getChannel();
		if (messageChannel != null) {
			this.channel = messageChannel.getName();
			this.channelId = messageChannel.getID();
		}
		this.message = message.getContent();
		this.timestamp = System.currentTimeMillis();

	}
	
}
