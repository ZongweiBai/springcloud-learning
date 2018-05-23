package com.baymin.kafkaproducer.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test"})
    public void receive(String message){
        System.out.println("test--消费消息:" + message);
    }

}
