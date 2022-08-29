package com.anandhu.kafka.OrderConsumer.customDeserializer;

import java.time.*;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class OrderConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.setProperty("bootstrap.servers", "localhost:9092");
		props.setProperty("key.deserializer", StringDeserializer.class.getName());
		props.setProperty("value.deserializer", OrderDeserializer.class.getName());
		
		
		props.setProperty("group.id", "OrderGroup");
		
		KafkaConsumer<String, Order> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("OrderTopic"));
		ConsumerRecords<String, Order> records = consumer.poll(Duration.ofSeconds(20));
		for(ConsumerRecord<String, Order> record:records)
		{
			String customerName = record.key();
			Order order = record.value();
			System.out.println("Customer name :"+ order.getCustomerName());
			System.out.println("Product: "+order.getProducts());
			System.out.println("Quantity: "+order.getQuantity());
			
		}
		
		consumer.close();
		
		

	}

}
