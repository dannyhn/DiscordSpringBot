package com.github.dannyhn.bot.service;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.client.listeners.MessageListener;
import com.github.dannyhn.bot.dto.MessageDTO;

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
	
	@Autowired
	private Queue<MessageDTO> messageList;
	
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
	
	@CacheEvict(value = {"messagelist"}, allEntries = true)
	@Scheduled(fixedRate = 600000, initialDelay = 10000)
	public void deleteOldMessages() {
		long currentTime = System.currentTimeMillis();
		long timePassed;
		MessageDTO head = messageList.peek();
		
		while (head != null) {
			timePassed = currentTime - head.getTimestamp();
			if (timePassed > 600000) {
				System.out.println("Message has been deleted: " + messageList.poll());
				head = messageList.peek();
			} else {
				break;
			}
		}
	}
	
}
