package com.example.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * log日志全局过滤器
 * @Author: hqf
 * @Date: 2023/4/23 9:43
 * <p>
 * 全局过滤器：针对所有请求，一旦配置就会投入使用，例如 lb: 就会默认使用loadbalancer默认过滤其
 * 局部过滤器：需要在路由中配置
 */
@Component
public class LogFilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("日志全局过滤器：" + exchange.getRequest().getPath().value());
        return chain.filter(exchange);
    }
}
