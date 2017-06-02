package com.github.dannyhn.bot.service;

import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.github.dannyhn.bot.dto.LogDTO;

@Component
public class LogService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Queue<String> logList;
	
	private static final String DISCORD_WEBHOOK_URL = "https://discordapp.com/api/webhooks/319967654578225153/b2r9WX9B_5m6I-FJuNESPKv8DVJlffk5OMm1G6-g3rWqAOMbqHVbxCgPoFLn4ZYH0_4x";
	
	@Async
	public void log(String msg) {
		System.out.println(msg);
		logList.add(msg);
	}
	
	@Scheduled(fixedRate = 300000, initialDelay = 10000)
	public void logMsgs() {
		String msg = "";
		while (!logList.isEmpty()) {
			msg += logList.poll() + "\n";
		}
		
		if ("".equals(msg)) {
			return;
		}
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("HeaderName", "value");
		headers.add("Content-Type", "application/json");

		HttpEntity<LogDTO> request = new HttpEntity<LogDTO>(new LogDTO(msg), headers);
		restTemplate.postForObject(DISCORD_WEBHOOK_URL, request, Object.class);
	}
	
}
