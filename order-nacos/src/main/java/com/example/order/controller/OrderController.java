package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author: hqf
 * @Date: 2023/4/17 15:20
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/add")
    public String add() {
        System.out.println("下单商品!");
        String s = restTemplate.getForObject("http://stock-service/stock/reduct", String.class);
        return "Hello World:" + s;
    }

    @RequestMapping("/header")
    public String header(@RequestHeader("X-Request-color") String color) {
        return "Hello:" + color;
    }



    @RequestMapping("/hello")
    public String hello() {
        return "Hello Order Sentinel";
    }


    /**
     * QPS流控测试
     */
    @RequestMapping("/flow")
    public String flow() {
        return "QPS流控测试，Flow QPS";
    }


    /**
     * 并发线程数测试
     */
    @RequestMapping("/flowThread")
    public String flowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return "并发线程数测试，FlowThread Thread";
    }


    /**
     * 流控模式 ： 关联测试
     * 关联资源：/order/add
     * 当 /order/add 达到流控限制则 /order/get 被流控
     */
    @RequestMapping("/get")
    public String get() {
        return "关联测试get";
    }


    /**
     * 流控效果 ： warm up
     * 针对激增流量，一段时间无人访问，突然洪峰流量大量涌入，然后又很长时间处于平稳
     * 预热/冷启动 ，流量突然增加直接把系统压垮，通过冷启动让流量缓慢增加在一定时间内增加到阈值上限
     * 例如：缓存预热 连接池，
     */
    @RequestMapping("/warmup")
    public String warmup() {
        return "warm up";
    }


    /**
     * 流控效果 ： 排队等待
     * 针对脉冲流量，隔一段时间空闲突然洪峰进来然后又空闲又洪峰，重复此动作，
     * 通过排队等待方式在空闲时间处理剩余流量
     */
    @RequestMapping("/queue")
    public String queue() {
        return "queue";
    }


    /**
     * 熔断降级: 慢调用比例
     */
    @RequestMapping("/ratio")
    public String ratio() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "熔断降级-慢调用比例";
    }

    /**
     * 熔断降级: 异常比例
     */
    @RequestMapping("/exception")
    public String exception() {
        int i = 1 / 0;
        return "熔断降级-异常比例";
    }

    /**
     * 熔断降级: 异常数量
     */
    @RequestMapping("/error")
    public String error() {
        int i = 1 / 0;
        return "熔断降级-异常数量";
    }





}
