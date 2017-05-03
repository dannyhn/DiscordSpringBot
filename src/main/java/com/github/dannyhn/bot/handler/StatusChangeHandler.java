package com.github.dannyhn.bot.handler;

import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;

import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.handle.obj.Status.StatusType;

/**
 * @author Danny
 *
 */
@Component
public class StatusChangeHandler {

	public void handleStatusChangeEvent(Status status) {
		if (status.getType().equals(StatusType.GAME)) {
			System.out.println("Someone is playing: " + status.getStatusMessage());
			// need to do persistent storage of default channel
		}
	}
	
}
