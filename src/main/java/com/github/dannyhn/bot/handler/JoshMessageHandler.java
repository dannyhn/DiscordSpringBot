package com.github.dannyhn.bot.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.github.dannyhn.bot.service.MessageService;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
@Component
public class JoshMessageHandler implements MessageHandler {

	@Autowired
	private MessageService messageService;
	
	@Override
	public void handleMessage(IMessage message) {
		String messageToSend;
		try {
			messageToSend = getPoem();
			messageService.sendMessage(message.getChannel(), messageToSend, message, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	private String getPoem() throws IOException {
		Resource resource = new ClassPathResource("joshpoem.txt");
		InputStream resourceInputStream = resource.getInputStream();
		String poem = IOUtils.toString(resourceInputStream, "UTF-8");
		String[] lines = poem.split("\n");
		if (lines.length > 0) {
			List<String> linesFiltered = new ArrayList<>();
			for (String line : lines) {
				if (!"".equalsIgnoreCase(line.trim()))
				{
					linesFiltered.add(line);
				}
			}
			Random random = new Random();
			return linesFiltered.get(random.nextInt(linesFiltered.size()));

		}
		return poem;
	}



	
}
