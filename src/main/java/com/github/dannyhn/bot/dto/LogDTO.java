package com.github.dannyhn.bot.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Danny
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LogDTO {

	private String content;
	
	@JsonCreator
	public LogDTO(@JsonProperty("content") String content) {
		this.content = content;
	}
	
}
