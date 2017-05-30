package com.github.dannyhn.bot.config;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.dannyhn.bot.client.constants.ClientConstants;
import com.github.dannyhn.bot.dto.MessageDTO;
import com.github.dannyhn.bot.util.ClientLoginUtil;
import com.github.dannyhn.sqlite.config.SqliteConfiguration;

import sx.blah.discord.api.IDiscordClient;

@Configuration
@EnableCaching
@Import(SqliteConfiguration.class)
public class DiscordBotConfiguration {
	
	@Bean
	public IDiscordClient discordClient() {
		return  ClientLoginUtil.createClient(ClientConstants.TOKEN, true);
	}
	
	@Bean
	public Queue<MessageDTO> messageList() {
		return new ConcurrentLinkedQueue<MessageDTO>();
	}

}
