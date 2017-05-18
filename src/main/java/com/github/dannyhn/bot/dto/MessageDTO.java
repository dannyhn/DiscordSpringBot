package com.github.dannyhn.bot.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO {
	
	private String channel;
	private String message;
	
	@JsonCreator
	public MessageDTO(@JsonProperty("channel") String channel, 
			@JsonProperty("message") String message) {
		this.channel = channel;
		this.message = message;
	}
	
}
