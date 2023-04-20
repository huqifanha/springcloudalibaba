package com.example.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: hqf
 * @Date: 2023/4/20 18:06
 */
@FeignClient(value = "stock-service", path = "/stock", fallback = StockFeignServiceFallBack.class)
public interface StockFeignService {

    @RequestMapping("/reduct")
    String reduct();
}
