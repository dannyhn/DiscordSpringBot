package com.github.dannyhn.bot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.client.constants.ClientConstants;
import com.github.dannyhn.bot.client.listeners.MessageListener;
import com.github.dannyhn.bot.util.ClientLoginUtil;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

@Component
public class BotService {

	@Autowired
	private MessageListener messageListener;
	
	@Scheduled(fixedRate = Long.MAX_VALUE, initialDelay = 10000)
	public void startup() {
		IDiscordClient client = ClientLoginUtil.createClient(ClientConstants.TOKEN, true);
		EventDispatcher dispatcher = client.getDispatcher(); 
		dispatcher.registerListener(messageListener);
	}
	
}
