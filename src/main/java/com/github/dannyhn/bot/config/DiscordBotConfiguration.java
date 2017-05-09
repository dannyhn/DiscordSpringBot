package com.github.dannyhn.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.dannyhn.bot.client.constants.ClientConstants;
import com.github.dannyhn.bot.util.ClientLoginUtil;

import sx.blah.discord.api.IDiscordClient;

@Configuration
public class DiscordBotConfiguration {
	
	@Bean
	public IDiscordClient discordClient() {
		return  ClientLoginUtil.createClient(ClientConstants.TOKEN, true);
	}

}
