package com.example.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hqf
 * @Date: 2023/4/17 15:20
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/reduct")
    public String reduct() {
        System.out.println("扣减库存!" + port);
        return "扣减库存" + port;
    }


}
