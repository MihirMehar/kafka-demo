package com.mihir.kafka_demo;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consume(String message) {
        System.out.println("📩 Notification Service received: " + message);
    }
}