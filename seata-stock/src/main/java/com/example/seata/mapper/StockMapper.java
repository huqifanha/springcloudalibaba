package com.example.seata.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seata.pojo.Stock;


public interface StockMapper extends BaseMapper<Stock> {

    void add(Stock stock);

    void deduct(Integer productId);

}
