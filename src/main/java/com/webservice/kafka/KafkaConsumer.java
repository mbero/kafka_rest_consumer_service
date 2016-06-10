package com.webservice.kafka;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.webservice.database.TweetsDBService;
import com.webservice.model.Tweet;
import com.webservice.rest.Tools;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;


public class KafkaConsumer extends Thread {
	final static String clientId = "SimpleConsumerDemoClient";
	final static String TOPIC = "test2";
	ConsumerConnector consumerConnector;

	public static void main(String[] argv) throws UnsupportedEncodingException {
		KafkaConsumer helloKafkaConsumer = new KafkaConsumer();
		helloKafkaConsumer.start();
	}

	public KafkaConsumer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "localhost:2181");
		properties.put("group.id", "test-group");
		properties.put("zookeeper.session.timeout.ms", "400");
		properties.put("zookeeper.sync.time.ms", "200");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("auto.offset.reset", "smallest");
		ConsumerConfig consumerConfig = new ConsumerConfig(properties);
		consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

	}

	@Override
	public void run() {
		TweetsDBService tweetsDBService = new TweetsDBService();
		Tools tools = new Tools();
		
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(TOPIC, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector
				.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = consumerMap.get(TOPIC).get(0);
		ConsumerIterator<byte[], byte[]> it = stream.iterator();
		int counter = 0;
		List<String> arrayOfMessagesToSave = new ArrayList<String>();
		while (it.hasNext()) {
		
					Tweet tweet = tools.convertStringToTweetClassObject(new String(it.next().message()));
					//Tweet tweet = new Tweet("test", "test", true);
					List<Tweet> listOfTweets = new ArrayList<Tweet>();
					listOfTweets.add(tweet);
					// save to db package of ten messages
					tweetsDBService.saveObjectsToDB(listOfTweets);
		
		}
	}

}
