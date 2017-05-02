package com.danny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

@SpringBootApplication
public class WordServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WordServiceApplication.class, args);
		 ClientBuilder clientBuilder = new ClientBuilder(); 
		    clientBuilder.withToken("MjcyMTk0MTAxODM3ODI0MDAx.C2SBPg.kb408ihyBOCfY31IzJtU0fXSWHU");
		    try {
		          clientBuilder.login(); 

		    } catch (DiscordException e) { 
		        e.printStackTrace();
		    }
	}
	
}
