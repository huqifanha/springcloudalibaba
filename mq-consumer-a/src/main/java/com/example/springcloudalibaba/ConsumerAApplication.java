package com.example.springcloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.Map;
import java.util.function.Consumer;


@SpringBootApplication
public class ConsumerAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerAApplication.class, args);
    }


    @Bean
    public Consumer<Message<Map<String, String>>> testchannel() {
        return msg -> {
            System.out.println("rocketmq-consumer-a Receive New Messages: " + msg.toString());
        };
    }


}
