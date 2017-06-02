package com.github.dannyhn.bot.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import com.github.dannyhn.bot.client.constants.ClientConstants;
import com.github.dannyhn.bot.dto.MessageDTO;
import com.github.dannyhn.bot.util.ClientLoginUtil;
import com.github.dannyhn.sqlite.config.SqliteConfiguration;

import sx.blah.discord.api.IDiscordClient;

@Configuration
@EnableCaching
@EnableAsync
@Import(SqliteConfiguration.class)
public class DiscordBotConfiguration {
	
	@Bean
	public IDiscordClient discordClient() {
		return  ClientLoginUtil.createClient(ClientConstants.TOKEN, true);
	}
	
	@Bean
	public Queue<MessageDTO> messageList() {
		return new ConcurrentLinkedQueue<MessageDTO>();
	}
	
	@Bean
	public RestTemplate restTemplate() throws Exception {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
		        .loadTrustMaterial(null, acceptingTrustStrategy)
		        .build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(csf)
		        .build();

		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}
	
	@Bean
	public Queue<String> logList() {
		return new ConcurrentLinkedQueue<String>();
	}
	

}
