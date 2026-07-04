package com.mihir.kafka_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public String placeOrder(@RequestBody String orderDetails) {
        kafkaTemplate.send("order-topic", orderDetails);
        return "Order placed and event sent to Kafka!";
    }
}