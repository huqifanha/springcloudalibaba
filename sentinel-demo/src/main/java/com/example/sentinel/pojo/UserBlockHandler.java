package com.example.sentinel.pojo;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @Author: hqf
 * @Date: 2023/4/20 10:40
 */
public class UserBlockHandler {
    /**
     * 注意：
     * 1.一定要public
     * 2.返回值与原方法保持一致, 参数一定要包含原方法参数，顺序一致
     * 3.可以在参数最后添加 BlockException，可以区分是什么规则的的异常处理方法
     */
    public static User blockHandlerForUser(String id, BlockException e) {
        System.out.println("规则：" + e.getRule().getResource());
        return new User("用户被流控！！！");
    }

}
