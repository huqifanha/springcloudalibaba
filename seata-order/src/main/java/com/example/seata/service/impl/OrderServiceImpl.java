package com.example.seata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seata.feign.StockFeignService;
import com.example.seata.mapper.OrderMapper;
import com.example.seata.pojo.Order;
import com.example.seata.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.skywalking.apm.toolkit.trace.Tag;
import org.apache.skywalking.apm.toolkit.trace.Tags;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: hqf
 * @Date: 2023/4/24 14:20
 */

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    StockFeignService stockFeignService;

    @GlobalTransactional
    @Override
    public String createOrder(Integer productId) {
        Order order = new Order();
        order.setProductId(productId);
        getBaseMapper().add(order);

        stockFeignService.deduct(productId);

        int i = 1 / 0;
        return "下单成功！";
    }

    @Override
    @Trace //加上这个注解才能记录
    @Tag(key = "all", value = "returnedObj") //记录返回值
//    @Tags({
//            @Tag(key = "all", value = "returnedObj"),
//            @Tag(key = "all", value = "arg[0]")
//    })
    // @Tags 记录返回参数
    public List<Order> all() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Order> list = getBaseMapper().all();
        return list;
    }


}
