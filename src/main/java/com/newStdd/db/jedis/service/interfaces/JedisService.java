package com.newStdd.db.jedis.service.interfaces;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Set;

import redis.clients.jedis.Jedis;

public interface JedisService {
	public Jedis getResource() throws IOException, InvalidPropertiesFormatException;
	public void returnResource(Jedis jedis);
	public Set<String> getKeys(String key) throws InvalidPropertiesFormatException, IOException;
//	public String get(String key) throws IOException, InvalidPropertiesFormatException;
//	public String set(String key, String value) throws IOException, InvalidPropertiesFormatException;
}
