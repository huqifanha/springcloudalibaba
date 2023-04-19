package com.example.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
//        return "Hello World:";
    }


}
