package com.example.seata.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seata.pojo.Order;

import java.util.List;

public interface OrderService extends IService<Order> {

    String createOrder(Integer productId);

    List<Order> all();

}
