package com.example.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hqf
 * @Date: 2023/4/17 15:20
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/{id}")
    public String get(@PathVariable Integer id) throws InterruptedException {
//        Thread.sleep(4000);
        System.out.println("查询商品：" + id);
        return "查询商品" + id + "：" + port;
    }


}
