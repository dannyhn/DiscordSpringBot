package com.github.dannyhn.bot.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Danny
 *
 */
@Component
public class RandomWordService implements InitializingBean{
	
	@Autowired
	private LogService log;
	
	private Map<String, List<String>> wordMap;
	private Random random = new Random();
	
	private final String animal = "animal";
	private final String adjBad = "adjBad";
	private final String adjBader = "adjBader";
	private final String verb = "verb";	
	private final String quote = "quote";
	
	
	/**
	 * returns a random animal
	 * 
	 * @return
	 */
	public String randomAnimal() {
		if (wordMap.containsKey(animal)) {
			return randomWordFromList(wordMap.get(animal));
		} else {
			return "monkey";
		}
	}	
	
	/**
	 * return a random bad adjective
	 * 
	 * @return
	 */
	public String randomAdjBad() {
		if (wordMap.containsKey(adjBad)) {
			return randomWordFromList(wordMap.get(adjBad));
		} else {
			return "tired";
		}
	}	
	
	/**
	 * returns a random bad adjective with a degree such as slower rather than slow
	 * 
	 * @return
	 */
	public String randomAdjBader() {
		if (wordMap.containsKey(adjBader)) {
			return randomWordFromList(wordMap.get(adjBader));
		} else {
			return "poorer";
		}
	}	
	
	/**
	 * returns a random verb
	 * 
	 * @return
	 */
	public String randomVerb() {
		if (wordMap.containsKey(verb)) {
			return randomWordFromList(wordMap.get(verb));
		} else {
			return "eat";
		}
	}
	
	/**
	 * returns a random verb
	 * 
	 * @return
	 */
	public String randomQuote() {
		if (wordMap.containsKey(quote)) {
			return randomWordFromList(wordMap.get(quote));
		} else {
			return "Oh my god, girls are the cows of people";
		}
	}
	
	/**
	 * @param lsOfWords
	 * @return
	 */
	private String randomWordFromList(List<String> lsOfWords) {
		int index = random.nextInt(lsOfWords.size());
		return lsOfWords.get(index);
	}
	
	/**
	 * @param is
	 * @return
	 */
	private String convertStreamToString(String fileName, ClassLoader classLoader) {
		Resource resource = new ClassPathResource(fileName);
		InputStream inputStream;
		try {
			inputStream = resource.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
			return "";
		}
		
//		InputStream inputStream = classLoader.getResourceAsStream(fileName);
//		if (inputStream == null) {
//			inputStream = classLoader.getResourceAsStream("resources/" + fileName);
//		}
		String theString = "";
		try {
			theString = IOUtils.toString(inputStream, "UTF-8");
		} catch (Exception e) {
			log.log("Error Converting InputStream");
			//System.out.println("Error Converting InputStream");
			e.printStackTrace();
		}
		IOUtils.closeQuietly(inputStream);
		return theString;

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		wordMap = new HashMap<String, List<String>>();
		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = RandomWordService.class.getClassLoader();
		try {
			String[] animalsArr = mapper.readValue(convertStreamToString("animals.json", classLoader), String[].class);
			wordMap.put(animal, Arrays.asList(animalsArr));
			String[] adjBadArr = mapper.readValue(convertStreamToString("adjBad.json", classLoader), String[].class);
			wordMap.put(adjBad, Arrays.asList(adjBadArr));
			String[] adjBaderArr = mapper.readValue(convertStreamToString("adjBader.json", classLoader), String[].class);
			wordMap.put(adjBader, Arrays.asList(adjBaderArr));
			String[] verbArr = mapper.readValue(convertStreamToString("verbs.json", classLoader), String[].class);
			wordMap.put(verb, Arrays.asList(verbArr));
			String[] quoteArr = mapper.readValue(convertStreamToString("quotes.json", classLoader), String[].class);
			wordMap.put(quote, Arrays.asList(quoteArr));
		} catch (IOException e) {
			log.log("Error on RandomWordUtil Init: " + e.getMessage());
			//System.out.println("Error on RandomWordUtil Init: " + e.getMessage());
		}
	}

}
