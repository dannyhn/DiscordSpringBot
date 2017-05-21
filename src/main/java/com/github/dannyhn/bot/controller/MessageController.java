package com.github.dannyhn.bot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.dannyhn.bot.dto.MessageDTO;
import com.github.dannyhn.bot.service.MessageService;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private IDiscordClient discordClient;
	
	@Autowired
	private Queue<MessageDTO> messageList;

	
	@RequestMapping(path="/message", method=RequestMethod.POST)
	public void sendMessage(@RequestBody MessageDTO message) {
		messageService.sendMessageToChannel(message.getChannel(), message.getMessage());
	}
	
	@RequestMapping(path="/messageUser", method=RequestMethod.POST)
	public void sendMessageToUser(@RequestBody MessageDTO message) throws MissingPermissionsException, RateLimitException, DiscordException {
		String userId = message.getUserId();
		discordClient.getUserByID(userId).getOrCreatePMChannel().sendMessage(message.getMessage());
	}
	
	@RequestMapping(path="/getChannels", method=RequestMethod.GET)
	public List<String> getChannels() {
		List<String> channels = new ArrayList<>();
		for (IChannel channel : discordClient.getChannels()) {
			channels.add(channel.getID() + " : " + channel.getName());
		}
		return channels;
	}
	
	@RequestMapping(path="/test", method=RequestMethod.GET)
	public List<MessageDTO> getAll() {
		List<MessageDTO> messages = new ArrayList<>();
		for (MessageDTO message : messageList) {
			messages.add(message);
		}
		return messages;
	}
	
	

}
