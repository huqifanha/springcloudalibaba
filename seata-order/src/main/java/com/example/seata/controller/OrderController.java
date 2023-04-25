package com.example.seata.controller;

import com.example.seata.feign.StockFeignService;
import com.example.seata.pojo.Order;
import com.example.seata.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: hqf
 * @Date: 2023/4/24 14:10
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    StockFeignService stockFeignService;
    @Autowired
    OrderService orderService;

    @RequestMapping("/add/{productId}")
    public String add(@PathVariable("productId") Integer productId) {
        String order = orderService.createOrder(productId);
        return order;
    }


    @RequestMapping("/all")
    public List<Order> all() {
        List<Order> all = orderService.all();
        return all;
    }


}
