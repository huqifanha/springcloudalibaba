package com.example.order.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 * @Author: hqf
 * @Date: 2023/4/18 18:01
 * 1.如果你在CustomLoadBalancerConfiguration 上面加了@Configuration 注解，使用到的服务必须显式的在启动类上配置好
 * 2.如果没有加@Configuration 注解那么配了的服务会使用配置的负载均衡策略，没有配的服务会使用默认的策略
 * 3.或者将对应的配置类放在Spring 的扫描包外,效果同第二点一样
 */
public class CustomLoadBalancerConfiguration {

    // 参考 NacosLoadBalancerClientConfiguration
    @Bean
    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment environment, LoadBalancerClientFactory loadBalancerClientFactory) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new RandomLoadBalancer(loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
    }

}
