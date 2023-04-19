package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @Author: hqf
 * @Date: 2023/4/19 11:44
 */
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigApplication.class, args);
//        while (true){
//            String userName = applicationContext.getEnvironment().getProperty("user.name");
//            String userAge = applicationContext.getEnvironment().getProperty("user.age");
//            String config = applicationContext.getEnvironment().getProperty("user.config");
//            System.out.println("user name :" + userName + "; age: " + userAge+ "; config: " + config);
//            TimeUnit.SECONDS.sleep(1);
//        }
    }


}
