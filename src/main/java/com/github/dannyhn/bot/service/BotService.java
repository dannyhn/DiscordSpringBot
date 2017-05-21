package com.github.dannyhn.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.client.listeners.MessageListener;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

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
	
	@Scheduled(fixedRate = 600000, initialDelay = 10000)
	public void randomMsg() throws MissingPermissionsException, RateLimitException, DiscordException {
		//discordClient.getUserByID("152300943000600576").getOrCreatePMChannel().sendMessage("HI");
		//System.out.println("Hi Message Send To Scumbag");
	}
	
}
