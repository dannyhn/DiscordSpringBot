package com.github.dannyhn.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.github.dannyhn.bot.client.constants.ClientConstants;
import com.github.dannyhn.bot.client.listeners.MessageListener;
import com.github.dannyhn.bot.util.ClientLoginUtil;
import com.github.dannyhn.sqlite.config.SqliteConfiguration;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

@SpringBootApplication
@EnableScheduling
@Import(SqliteConfiguration.class)
public class DiscordSpringBotApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DiscordSpringBotApplication.class, args);
	}
	
}
