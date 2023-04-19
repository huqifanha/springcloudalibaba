package com.example.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hqf
 * @Date: 2023/4/19 15:48
 */
@RestController
@RequestMapping("/config")
@RefreshScope // 使用@Value获取nacos配置加上此注解才能动态刷新
public class ConfigController {

    @Value("${user.name}")
    public String userName;

    @RequestMapping("/show")
    public String show() {
        return "Hello Nacos Config:" + userName;
    }

}
