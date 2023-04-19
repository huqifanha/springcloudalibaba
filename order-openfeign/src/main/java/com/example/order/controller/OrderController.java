package com.example.order.controller;

import com.example.order.feign.ProductFeignService;
import com.example.order.feign.StockFeignService;
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
    StockFeignService stockFeignService;
    @Autowired
    ProductFeignService productFeignService;

    @RequestMapping("/add")
    public String add() {
        System.out.println("下单商品!");
        String s = stockFeignService.reduct();
        String p = productFeignService.get(111);
        return "Hello OpenFeign:" + p + s;
    }


}
