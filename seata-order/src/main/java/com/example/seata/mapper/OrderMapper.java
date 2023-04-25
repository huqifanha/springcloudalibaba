package com.example.seata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seata.pojo.Order;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {

    void add(Order order);

    List<Order> all();


}
