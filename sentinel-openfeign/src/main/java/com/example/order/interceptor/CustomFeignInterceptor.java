package com.example.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hqf
 * @Date: 2023/4/19 10:45
 * 自定义openfeign拦截器
 * 配置文件也可配置
 */
//@Configuration
public class CustomFeignInterceptor implements RequestInterceptor {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void apply(RequestTemplate requestTemplate) {

        // TODO

        // 可以修改请求头，参数等等
        requestTemplate.header("aaa", "aaa");
        requestTemplate.query("id", "111");

        logger.info("openfeign 拦截器");
    }
}
