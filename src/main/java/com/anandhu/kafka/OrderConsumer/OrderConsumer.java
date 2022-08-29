package com.anandhu.kafka.OrderConsumer;

import java.time.*;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class OrderConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
		
		
		props.setProperty("group.id", "OrderGroup");
		
		KafkaConsumer<String, Integer> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("OrderTopic"));
		ConsumerRecords<String, Integer> orders = consumer.poll(Duration.ofSeconds(20));
		for(ConsumerRecord<String, Integer> order:orders)
		{
			System.out.println("Product name "+order.key());
			System.out.println("Quantity "+order.value());
		}
		
		consumer.close();
		
		

	}

}
