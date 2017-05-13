package com.github.dannyhn.bot.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private String id;
	private String name;
	private String pubgUrl;
	private String lastGame;
	private String note;
	
	@JsonCreator
	public User(@JsonProperty("id") String id, 
			@JsonProperty("name") String name,
			@JsonProperty("pubgUrl") String pubgUrl,
			@JsonProperty("lastGame") String lastGame,
			@JsonProperty("note") String note) {
		this.id = id;
		this.name = name;
		this.pubgUrl = pubgUrl;
		this.lastGame = lastGame;
		this.note = note;
	}
	
	public void setProperty(String propertyName, String propertyValue) {
		switch(propertyName) {
		case "note":
			this.note = propertyValue;
			return;
		case "pubgUrl":
			this.pubgUrl = propertyValue;
			return;
		default:
			return;
		}
	}

}
