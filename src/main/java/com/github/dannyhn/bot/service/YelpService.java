package com.github.dannyhn.bot.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dannyhn.cache.KCache;
import com.github.dannyhn.cache.KittyCache;
import com.github.dannyhn.yelp.Business;
import com.github.dannyhn.yelp.YelpAPI;
import com.github.dannyhn.yelp.YelpConstants;

/**
 * @author Danny
 *
 */
@Component
public class YelpService {
	
	@Autowired
	private ContextService contextService;
	
	private KCache<String, List<Business>> cache;
	
	private YelpAPI yelp;
	private Random random = new Random();
		

	public String getYelpInfoFromZipCode(String zipcode) {
		if (yelp == null || cache == null) {
			//yelp = new YelpAPI("", "", "", "");
			yelp = new YelpAPI(YelpConstants.CONSUMER_KEY, YelpConstants.CONSUMER_SECRET, YelpConstants.TOKEN, YelpConstants.TOKEN_SECRET);
			cache = new KittyCache<String, List<Business>>(100);
		}
		List<Business> business = getBusinessFromZipCode(zipcode);
		if (business == null) {
			return "No Restaurant found";
		}
		Business randomBusiness = randomBusinessFromList(business);
		removeEntry(zipcode, randomBusiness);
		return randomBusiness.toString();
	}
	
	public String getYelpListInfoFromZipCode(String zipcode) {
		if (yelp == null || cache == null) {
			//yelp = new YelpAPI("", "", "", "");
			yelp = new YelpAPI(YelpConstants.CONSUMER_KEY, YelpConstants.CONSUMER_SECRET, YelpConstants.TOKEN, YelpConstants.TOKEN_SECRET);
			cache = new KittyCache<String, List<Business>>(100);
		}
		List<Business> business = getBusinessFromZipCode(zipcode);
		if (business == null) {
			return "No Restaurant found";
		}
		String result = "";
		int index = 0;
		for (Business restaurant : business) {
			result += index++ + ") " + restaurant.getName() + "\n";
		}
		contextService.addBusiness(business);
		return result;
	}
	
	private Business randomBusinessFromList(List<Business> lsOfWords) {
		int index = random.nextInt(lsOfWords.size());
		return lsOfWords.get(index);
	}
	
	/**
	 * Gets business in cache if exists
	 * else get from yelp
	 * 
	 * @param zipcode
	 * @return
	 */
	private List<Business> getBusinessFromZipCode(String zipcode) {
		List<Business> business = cache.get(zipcode);
		if (business == null) {
			business = yelp.queryAPI(zipcode);
			if (business != null) {
				cache.put(zipcode, business, 600);
			}
		}
		return business;

	}
	
	private void removeEntry(String zipcode, Business businessToDelete) {
		List<Business> business = cache.get(zipcode);
		if (business != null) {
			business.remove(businessToDelete);
			if (business.isEmpty()) {
				cache.remove(zipcode);
			}
		}
	}
	
}
