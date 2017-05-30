package com.github.dannyhn.sqlite.client;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SqliteObjectClient<T> {
	
	@Autowired
	private SqliteClient client;
	
	@Autowired
	private ObjectMapper mapper;
	
	public T read(String key, Class<T> clazz) {
		String content = client.read(key);
		if ("error".equals(content)) {
			return null;
		} else {
			try {
				return mapper.readValue(content, clazz);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Could not Parse: " + content);
				return null;
			}
		}
	}
	
	public void write(String key, T value) {
		try {
			client.write(key, mapper.writeValueAsString(value));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
