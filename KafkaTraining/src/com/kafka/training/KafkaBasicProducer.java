package com.kafka.training;

import java.util.Properties;
import java.io.Console;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaBasicProducer {
	public static void main(String args[]) throws Exception {
		
		String input = "";
		Boolean doExit = false;
		String topicName = System.console().readLine("ENTER TOPIC NAME: ");
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");         
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		
		System.out.println("Enter key-value pair delimited with ','... ");
		do {
			input = System.console().readLine();
			
			if (input.trim() != "exit") {
				String[] keyValuePairs = input.split(",");
				producer.send(new ProducerRecord<String, String>(topicName, keyValuePairs[0], keyValuePairs[1]));
			}
			else {
				doExit = true;
			}
		} while (!doExit);
		
		System.out.println("Message sent successfully");
	    producer.close();
	    
	}
}
