package com.example.order.config;

import com.example.order.interceptor.CustomFeignInterceptor;
import feign.Logger;
import feign.Request;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @Author: hqf
 * @Date: 2023/4/19 9:59
 * 全局配置 : 当使用@Configuration配置就会全局生效，所有的服务提供方都会使用
 * 局部配置 : 1.去掉@Configuration，通过配置类针对某一个服务配置FeignClient(name = "stock-service", configuration = FeignConfig.class )
 * 2. 通过配置文件方式配置
 */
//@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    /**
     * 还原原生注解，在配置文件配置
     */
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

    /**
     * 配置连接时间和超时时间，在配置文件配置
     */
//    @Bean
//    public Request.Options options() {
//        return new Request.Options(5000, TimeUnit.MILLISECONDS, 10000, TimeUnit.MILLISECONDS, false);
//    }


}
