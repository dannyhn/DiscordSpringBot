package com.github.dannyhn.bot.service;

import org.springframework.stereotype.Component;

import com.github.dannyhn.cache.KCache;
import com.github.dannyhn.cache.KittyCache;

/**
 * @author Danny
 *
 */
@Component
public class RateLimitingService {
	
	private KCache<String, Integer> userCache = new KittyCache<String, Integer>(100);

	private int counter;
	
	private long previousTime = 0;
	
	
	private static final int userLimit = 10;
	private static final int botLimit = 20;
	
	public boolean canMakeRequest(String name) {
		 if (!canUserMakeRequest(name)) {
			 return false;
		 } else {
			 return canBotMakeRequest();
		 }
	}

	private boolean canBotMakeRequest() {
		if (counter > botLimit) {
			 return false;
		 }
		 counter++;
		 int secondsPassed = getSeconds();
		 counter -= (secondsPassed / 5);
		 return true;
	}

	private boolean canUserMakeRequest(String name) {
		Integer userRequests = userCache.get(name);
		if (userRequests == null) {
			userRequests = 0;
		}
		if (userRequests > userLimit) {
			return false;
		}
		userCache.put(name, userRequests + 1, 60);
		return true;
	}
	
	/**
	 * used to get time between request
	 * 
	 * @return
	 */
	private int getSeconds() {
		if (previousTime == 0) {
			previousTime = System.currentTimeMillis();
			return 0;
		} else {
			long currentTime = System.currentTimeMillis();
			int seconds = (int) ((currentTime - previousTime) / 1000);
			previousTime = currentTime;
			return seconds;
		}
	}

	
	
}
