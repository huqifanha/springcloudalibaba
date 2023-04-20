package com.example.order.feign;

import org.springframework.stereotype.Component;

/**
 * @Author: hqf
 * @Date: 2023/4/20 18:17
 */
@Component
public class StockFeignServiceFallBack implements StockFeignService {
    @Override
    public String reduct() {
        return "降级了！";
    }
}
