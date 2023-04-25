package com.example.seata.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hqf
 * @Date: 2023/4/24 14:13
 */
@FeignClient(name = "seata-stock-service", path = "/stock")
public interface StockFeignService {

    @RequestMapping("/deduct/{productId}")
    String deduct(@PathVariable("productId") Integer productId);

}
