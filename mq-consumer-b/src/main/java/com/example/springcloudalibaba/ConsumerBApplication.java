package com.example.springcloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;
import java.util.function.Consumer;


@SpringBootApplication
public class ConsumerBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerBApplication.class, args);
    }

    @Bean
    public Consumer<Message<String>> testchannel() {
        return msg -> {
            System.out.println("rocketmq-consumer-b Receive New Messages: " + msg.toString());
        };
    }

    @Bean
    public Consumer<Message<String>> testchannel1() {
        return msg -> {
            System.out.println("rocketmq-consumer-b Receive New Messages: " + msg.toString());
        };
    }



}
