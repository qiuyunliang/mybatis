package me.ciu.o.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class KafkaProducer {

    @Autowired
    KafkaTemplate kafkaTemplate;

    public static final List<String> lines = Arrays.asList("hello word", "you are welcom", "china ningde");

    public void start(){

        while (true) {
            try {
                Thread.sleep(100);
                kafkaTemplate.send("flink", "key", lines.get(new Random().nextInt(3)));
                kafkaTemplate.send("test", "key", lines.get(new Random().nextInt(3)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}