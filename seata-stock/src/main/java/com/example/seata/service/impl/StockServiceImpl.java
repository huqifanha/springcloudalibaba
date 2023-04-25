package com.example.seata.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seata.mapper.StockMapper;
import com.example.seata.pojo.Stock;
import com.example.seata.service.StockService;
import org.springframework.stereotype.Service;

/**
 * @Author: hqf
 * @Date: 2023/4/24 14:20
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Override
    public void add(Stock stock) {
        getBaseMapper().add(stock);
    }

    @Override
    public void deduct(Integer productId) {
        getBaseMapper().deduct(productId);
    }


}
