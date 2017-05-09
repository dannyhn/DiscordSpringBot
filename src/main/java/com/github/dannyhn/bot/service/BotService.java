package com.github.dannyhn.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.client.listeners.MessageListener;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

@Component
public class BotService {

	@Autowired
	private MessageListener messageListener;
	
	@Autowired
	private IDiscordClient discordClient;
	
	@Scheduled(fixedRate = Long.MAX_VALUE, initialDelay = 10000)
	public void startup() {
		EventDispatcher dispatcher = discordClient.getDispatcher(); 
		dispatcher.registerListener(messageListener);
	}
	
}
