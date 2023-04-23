package com.example.gateway.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言
 * <p>
 * 1.必须是spring的bean
 * 2.类名必须加上RoutePredicateFactory结尾
 * 3.必须继承AbstractRoutePredicateFactory
 * 4.必须声明静态的内部类，声明属性来接收配置文件中对应的断言信息，属性要有get set方法
 * 5.需要结合shortcutFieldOrder进行绑定
 * 6.通过apply方法进行判断，true成功
 */
@Component
public class CheckAuthRoutePredicateFactory
        extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {


    public CheckAuthRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {

                if (config.getName().equals("hqf")) {
                    return true;
                }
                return false;
            }

        };
    }


    // 用于接收配置文件中 断言的信息
    @Validated
    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}