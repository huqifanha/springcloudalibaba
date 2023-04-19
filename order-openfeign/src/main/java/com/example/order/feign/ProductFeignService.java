package com.example.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * name 制定调用rest接口所在的服务名
 * path 制定调用rest接口所在Controller指定的@RequestMapping ， 所在Controller没有指定则不需要
 */
@FeignClient(name = "product-service", path = "/product")
public interface ProductFeignService {

    // rest接口参数必须要@PathVariable("id")对应名称
    @RequestMapping("/{id}")
    String get(@PathVariable("id") Integer id);

}



/*
@RestController
@RequestMapping("/stock")
public class StockController {

    @Value("${server.port}")
    String port;
    @RequestMapping("/reduct")
    public String reduct() {
        System.out.println("扣减库存!" + port);
        return "扣减库存" + port;
    }
}*/
