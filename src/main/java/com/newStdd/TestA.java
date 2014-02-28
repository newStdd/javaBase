package com.newStdd;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;
import kafka.message.Message;
import kafka.producer.ProducerConfig;

import redis.clients.jedis.Jedis;

import com.newStdd.db.jedis.service.implement.JedisImplement;
import com.newStdd.util.DateUtil;

public class TestA {
	public static String getMessage(Message message) {
		ByteBuffer buffer = message.payload();
    byte [] bytes = new byte[buffer.remaining()];
    buffer.get(bytes);
    return new String(bytes);
	}    
	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {
		/*获取keys*/
//		JedisImplement jedisImplement= new JedisImplement();
//		Set<String> keys= new HashSet<String>();
//		Jedis jedis= jedisImplement.getResource();
//		keys= jedisImplement.getKeys("J*");
//		for (String key: keys) {
//			System.out.println(key);
//		}
//		jedisImplement.returnResource(jedis);
		/*读kafka*/
//		Properties props = new Properties();
//		props.put("zk.connect", "10.1.249.31:2181,10.1.249.32:2181,10.1.249.33:2181");
//		props.put("zk.connectiontimeout.ms", "1000000");
//		props.put("autocommit.interval.ms", "1000");
////		props.put("consumer.timeout.ms", "0");
//		props.put("groupid", "Spout");			
//		ConsumerConfig consumerConfig = new ConsumerConfig(props);
//		ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
//		Map<String, List<KafkaStream<Message>>> topicMessageStreams= consumerConnector.createMessageStreams(ImmutableMap.of("NETLOG", 1));
//		List<KafkaStream<Message>> streams = topicMessageStreams.get("NETLOG");
//		KafkaStream<Message> stream = streams.get(0);
//		ConsumerIterator<Message> _it = stream.iterator();
//		while(_it.hasNext()) {
//			System.out.println(getMessage(_it.next().message()));
//		}
		/*写kafka*/
		Properties props = new Properties();
		props.put("zk.connect", "10.1.249.31:2181,10.1.249.32:2181,10.1.249.33:2181");
		props.put("zk.connectiontimeout.ms", "1000000");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		ProducerConfig config = new ProducerConfig(props);
		Producer<String, String> producer = new Producer<String, String>(config);			
		ProducerData<String, String> data = new ProducerData<String, String>("RECOM", "15652317798|1372821577170|||||||||美食|东单|全聚德||");
		producer.send(data);		
		producer.close();
	}
}
