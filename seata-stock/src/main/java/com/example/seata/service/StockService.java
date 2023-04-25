package com.example.seata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seata.pojo.Stock;

public interface StockService extends IService<Stock> {

    void add(Stock stock);

    void deduct(Integer productId);

}
