package me.ciu.o.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    // 使用@KafkaListener注解, 可以指定: 主题, 分区, 消费组
    @KafkaListener(topics = {"test"})
    public void receive(String message){
        System.out.println("Consumer received a message: " + message);
    }
}