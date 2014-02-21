package com.newStdd.db.jedis.service.implement;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Set;

import com.newStdd.db.jedis.service.interfaces.JedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisImplement implements JedisService {
	private static JedisPool jedisPool;
	public static String hostPortList;
	
	public Jedis getResource() throws IOException, InvalidPropertiesFormatException {
			if (jedisPool== null) {
				JedisPoolConfig jedisPoolConfig= new JedisPoolConfig();
				Properties pro = new Properties();
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream inputStream = classLoader.getResourceAsStream("redisConfig.xml");
				pro.loadFromXML(inputStream);
				inputStream.close() ;
				String maxActive = pro.getProperty("maxActive");
				String maxIdle = pro.getProperty("maxIdle");
				String maxWait = pro.getProperty("maxWait");
				String host = pro.getProperty("host");
				String port = pro.getProperty("port");
				hostPortList= pro.getProperty("hostPortList");
				Boolean testOnBorrow= Boolean.getBoolean(pro.getProperty("testOnBorrow"));
				jedisPoolConfig.setMaxActive(Integer.parseInt(maxActive));
				jedisPoolConfig.setMaxIdle(Integer.parseInt(maxIdle));
				jedisPoolConfig.setMaxWait(Integer.parseInt(maxWait));
				jedisPoolConfig.setTestOnBorrow(testOnBorrow);				
				jedisPool = new JedisPool(jedisPoolConfig, host, Integer.parseInt(port));
			}
			return jedisPool.getResource();
	}
	
	public Set<String> getKeys(String key) throws InvalidPropertiesFormatException, IOException {
		Jedis jedisSingle;
		String[] hostPortArrays, hostPortArray, port;
		int portStart, portEnd;
		String host;
		Set<String> keys= new HashSet<String>();
		
		hostPortArrays= hostPortList.split("\\|");
		for (String hostPort: hostPortArrays) {
			hostPortArray= hostPort.split(":");
			host= hostPortArray[0];
			port= hostPortArray[1].split("-");
			portStart= Integer.parseInt(port[0]);
			portEnd= Integer.parseInt(port[1]);
			for (int i=portStart; i<=portEnd; i++) {
				jedisSingle= new Jedis(host,i);
				keys.addAll(jedisSingle.keys(key));
				jedisSingle.disconnect();
			}
		}
		return keys;
	}

	public void returnResource(Jedis jedis) {
		jedisPool.returnResource(jedis);
	}
	
//	public String get(String key) throws InvalidPropertiesFormatException, IOException {
//		Jedis jedis = null;
//		String str = "";
//		jedis = getResource();
//		str = jedis.get(key);
//		returnResource(jedis);
//		return str;		
//	}	
//	
//	public String set(String key, String value) throws InvalidPropertiesFormatException, IOException {
//		Jedis jedis = null;
//		String str = "";
//		jedis = getResource();
//		str = jedis.set(key, value);
//		returnResource(jedis);
//		return str;		
//	}		
}
