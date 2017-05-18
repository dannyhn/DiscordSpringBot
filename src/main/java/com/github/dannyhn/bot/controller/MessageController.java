package com.github.dannyhn.bot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.dannyhn.bot.dto.MessageDTO;
import com.github.dannyhn.bot.service.MessageService;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IChannel;

@RestController
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private IDiscordClient discordClient;
	
	@RequestMapping(path="/message", method=RequestMethod.POST)
	public void sendMessage(@RequestBody MessageDTO message) {
		messageService.sendMessageToChannel(message.getChannel(), message.getMessage());
	}
	
	@RequestMapping(path="/getChannels", method=RequestMethod.GET)
	public List<String> getChannels() {
		List<String> channels = new ArrayList<>();
		for (IChannel channel : discordClient.getChannels()) {
			channels.add(channel.getID() + " : " + channel.getName());
		}
		return channels;
	}
	
	

}
