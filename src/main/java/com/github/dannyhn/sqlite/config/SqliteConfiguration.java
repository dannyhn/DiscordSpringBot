package com.github.dannyhn.sqlite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan("com.github.dannyhn.sqlite")
public class SqliteConfiguration {
	
	@Bean
	public ObjectMapper mapper() {
		return new ObjectMapper();
	}

}
