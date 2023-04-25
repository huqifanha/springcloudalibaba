package com.example.seata.controller;

import com.example.seata.pojo.Stock;
import com.example.seata.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hqf
 * @Date: 2023/4/24 14:10
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    StockService stockService;

    @RequestMapping("/deduct/{productId}")
    public String deduct(@PathVariable("productId") Integer productId) {
        stockService.deduct(productId);
        System.out.println("扣减库存成功!!!");
        return "扣减库存成功";
    }

    @RequestMapping("/add")
    public Stock add() {
        Stock stock = new Stock();
        stock.setProductId(9);
        stock.setCount(100);
        stockService.add(stock);
        return stock;
    }

    @RequestMapping("/get")
    public Stock get() {
        Stock stock = stockService.getById(4);
        return stock;
    }


}
