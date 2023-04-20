package com.example.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.example.sentinel.pojo.User;
import com.example.sentinel.pojo.UserBlockHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: hqf
 * @Date: 2023/4/20 10:22
 */
@RestController
@RequestMapping("/sentinel")
public class SentinelController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String RESOURCE_NAME = "hello";
    private static final String USER_RESOURCE_NAME = "user";
    private static final String DEGRADE_RESOURCE_NAME = "degrade";

    @RequestMapping("/hello")
    public String hello() {
        Entry entry = null;
        try {
            entry = SphU.entry(RESOURCE_NAME);
            return "hello!";
        } catch (BlockException e) {
            logger.info("block");
            return "被流控！！！";
        } catch (Exception e) {
            Tracer.traceEntry(e, entry);
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }
        return null;
    }


    /**
     * @SentinelResource 在接口中资源定义被流控处理方式
     * 1. 添加依赖 sentinel-annotation-aspectj
     * 2.配置 bean - SentinelResourceAspect
     * <p>
     * value 定义资源
     * blockHandler 设置被流控降级的处理方法（默认方法必须生命在一个类中）
     * 如果不想再同一个类中, 需要指定 blockHandlerClass ，方法必须是 static
     * <p>
     * fallback 接口出现异常处理方式
     * 如果不想再同一个类中, 需要指定 fallbackClass ，方法必须是 static
     * <p>
     * blockHandler 和 fallback 同时指定，blockHandler优先级更高
     */
    @RequestMapping("/user")
    @SentinelResource(value = USER_RESOURCE_NAME,
//            fallback = "fallbackHandlerForUser",
            blockHandlerClass = {UserBlockHandler.class},
            blockHandler = "blockHandlerForUser"
    )
    public User getUser(String id) {
        return new User("hqf");
    }

    /**
     * 注意：
     * 1.一定要public
     * 2.返回值与原方法保持一致, 参数一定要包含原方法参数，顺序一致
     * 3.在参数最后添加BlockException，可以区分是什么规则的的异常处理方法
     */
    public User fallbackHandlerForUser(String id, Throwable e) {
        System.out.println("发生异常：" + e.getMessage());
        return new User("异常处理！");
    }


    /**
     * 注意：
     * 1.一定要public
     * 2.返回值与原方法保持一致, 参数一定要包含原方法参数，顺序一致
     * 3.在参数最后添加BlockException，可以区分是什么规则的的异常处理方法
     */
    public User blockHandlerForUser(String id, BlockException e) {
        return new User("用户被流控！！！");
    }


    /**
     * 定义流控规则
     * <p>
     * <p>
     * spring 的初始化方法
     */
    @PostConstruct
    private static void initFlowRules() {
        List<FlowRule> flowRules = new ArrayList<>();

        FlowRule flowRule = new FlowRule();
        // 为哪个资源进行流控
//        flowRule.setResource(RESOURCE_NAME);
        flowRule.setResource(USER_RESOURCE_NAME);
        // 设置流控规则 ， QPS
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 设置受保护资源阈值
        flowRule.setCount(1);
        flowRules.add(flowRule);

        // 加载配置规则
        FlowRuleManager.loadRules(flowRules);
    }


    @RequestMapping("/degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME, blockHandler = "blockHandlerFordDegrade")
    public User degrade(String id) {
        throw new RuntimeException("运行异常！！！");
    }

    public static User blockHandlerFordDegrade(String id, BlockException e) {
        System.out.println("规则：" + e.getRule().getResource());
        return new User("熔断降级！！！");
    }


    /**
     * 定义降级规则
     * <p>
     * <p>
     * spring 的初始化方法
     */
    @PostConstruct
    private static void initDegradeRules() {
        List<DegradeRule> degradeRules = new ArrayList<>();

        DegradeRule degradeRule = new DegradeRule();
        // 为哪个资源进行降级
        degradeRule.setResource(DEGRADE_RESOURCE_NAME);
        // 设置降级规则 ， 异常数
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);

        // 触发熔断异常数量，运行过程会出现三次，源码中判断是 大于 异常数量， 所以次数会 +1
        degradeRule.setCount(2);
        // 触发熔断最小请求数量
        degradeRule.setMinRequestAmount(2);
        // 统计时长，多长时间内累计触发异常
        degradeRule.setStatIntervalMs(60 * 1000);
        // 规则：一分钟内，请求2次，触发了2次异常

        // 熔断持续时长，单位 s
        //一旦触发了熔断，再次请求对应接口会调用降级方法
        //10s后处于半开状态，恢复接口请求调用，但是一旦第一次请求异常就在此熔断，不会根据设置规则进行判定
        degradeRule.setTimeWindow(10);

        degradeRules.add(degradeRule);

        // 加载配置规则
        DegradeRuleManager.loadRules(degradeRules);
    }


}
