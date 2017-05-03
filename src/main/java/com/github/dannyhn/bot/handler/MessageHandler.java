package com.github.dannyhn.bot.handler;

import sx.blah.discord.handle.obj.IMessage;

/**
 * stuffs
 * 
 * @author Danny
 *
 */
public interface MessageHandler {

	/**
	 * handles Messages
	 * 
	 * @param message
	 */
	public void handleMessage(IMessage message);

}
