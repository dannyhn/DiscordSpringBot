package com.github.dannyhn.bot.handler.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.message.handler.AdminMessageHandler;
import com.github.dannyhn.bot.message.handler.ComplimentMessageHandler;
import com.github.dannyhn.bot.message.handler.ContextMessageHandler;
import com.github.dannyhn.bot.message.handler.DotaMessageHandler;
import com.github.dannyhn.bot.message.handler.HelpMessageHandler;
import com.github.dannyhn.bot.message.handler.InfoMessageHandler;
import com.github.dannyhn.bot.message.handler.InsultMessageHandler;
import com.github.dannyhn.bot.message.handler.JoshMessageHandler;
import com.github.dannyhn.bot.message.handler.LastGameMessageHandler;
import com.github.dannyhn.bot.message.handler.MessageHandler;
import com.github.dannyhn.bot.message.handler.PlayerMessageHandler;
import com.github.dannyhn.bot.message.handler.ProfanityMessageHandler;
import com.github.dannyhn.bot.message.handler.PubgMessageHandler;
import com.github.dannyhn.bot.message.handler.QuoteMessageHandler;
import com.github.dannyhn.bot.message.handler.ReadyMessageHandler;
import com.github.dannyhn.bot.message.handler.RollMessageHandler;
import com.github.dannyhn.bot.message.handler.StevenMessageHandler;
import com.github.dannyhn.bot.message.handler.YelpMessageHandler;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Factory that determines which MessageHandler to use
 * 
 * @author Danny
 *
 */
@Component
public class MessageHandlerFactory {
	
	@Autowired
	private InfoMessageHandler infoMessageHandler;

	@Autowired
	private StevenMessageHandler stevenMessageHandler;

	@Autowired
	private YelpMessageHandler yelpMessageHandler;
	
	@Autowired
	private ComplimentMessageHandler complimentMessageHandler;
	
	@Autowired
	private QuoteMessageHandler quoteMessageHandler;
	
	@Autowired
	private HelpMessageHandler helpMessageHandler;
	
	@Autowired
	private InsultMessageHandler insultMessageHandler;
	
	@Autowired
	private ReadyMessageHandler readyMessageHandler;
	
	@Autowired
	private RollMessageHandler rollMessageHandler;
	
	@Autowired
	private PlayerMessageHandler playerMessageHandler;
	
	@Autowired
	private ProfanityMessageHandler profanityMessageHandler;
	
	@Autowired
	private ContextMessageHandler contextMessageHandler;
	
	@Autowired
	private LastGameMessageHandler lastGameMessageHandler;
	
	@Autowired
	private JoshMessageHandler joshMessageHandler;
	
	@Autowired
	private PubgMessageHandler pubgMessageHandler;
	
	@Autowired
	private AdminMessageHandler adminMessageHandler;
	
	@Autowired
	private DotaMessageHandler dotaMessageHandler;
	
	/**
	 * @param message
	 * @return
	 */
	public MessageHandler getMessageHandler(IMessage message) {
		
		String lowerCasedMessage = message.getContent().toLowerCase();
		
		if (message.getContent().startsWith(".info")) {
			return infoMessageHandler;
		} else if (lowerCasedMessage.startsWith(".steven")) {
			return stevenMessageHandler;
		} else if (lowerCasedMessage.startsWith(".yelp")) {
			return yelpMessageHandler;
		} else if (lowerCasedMessage.startsWith(".c")) {
			return complimentMessageHandler;
		} else if (lowerCasedMessage.startsWith(".quote")) {
			return quoteMessageHandler;
		} else if (lowerCasedMessage.startsWith(".help")) {
			return helpMessageHandler;
		} else if (lowerCasedMessage.startsWith(".i")) {
			return insultMessageHandler;
		} else if (lowerCasedMessage.startsWith(".ready")) {
			return readyMessageHandler;
		} else if (lowerCasedMessage.startsWith(".roll")) {
			return rollMessageHandler;
		} else if (lowerCasedMessage.startsWith(".playerinfo")) {
			return playerMessageHandler;
		} else if (isProfanity(message)) {
			return profanityMessageHandler;
		} else if(lowerCasedMessage.startsWith(".lastgame"))  {
			return lastGameMessageHandler;
		} else if (lowerCasedMessage.startsWith(".josh")) {
			return joshMessageHandler;
		} else if (lowerCasedMessage.startsWith(".pubg")) {
			return pubgMessageHandler;
		} else if (lowerCasedMessage.startsWith(".dota")) {
			return dotaMessageHandler;
		} else if (lowerCasedMessage.startsWith(".admin")) { 
			return adminMessageHandler;
		} else {
			return contextMessageHandler;
		}

	}
	
	private boolean isProfanity(IMessage message) {
		return message.getContent().toLowerCase().contains("fag");
	}
}
