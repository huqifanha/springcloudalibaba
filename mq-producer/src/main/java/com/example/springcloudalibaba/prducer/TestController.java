package com.example.springcloudalibaba.prducer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private StreamBridge streamBridge;


    @GetMapping("/send")
    public String send() {
        for (int i = 0; i < 50; i++) {
            Map<String, String> map = new HashMap<>();
            String id = i + "";
            map.put("id", id);
            map.put("msg", "测试消息");
            MessageBuilder<Map<String, String>> builder = MessageBuilder.withPayload(map);
            boolean send = streamBridge.send("testchannel-out-0", builder.build());
            System.out.println("消息" + id + "，发送状态：" + send);
        }
        return "消息发送成功！";
    }


    @GetMapping("/send1")
    public String send1() {
        for (int i = 0; i < 5; i++) {
            Map<String, String> map = new HashMap<>();
            String id = i + "";
            map.put("id", id);
            map.put("msg", "测试消息" + 1);
            MessageBuilder<Map<String, String>> builder;

            if (i == 1) {
                builder = MessageBuilder.withPayload(map)
                        .setHeader("TAGS", "AAA");
            } else if (i == 2) {
                builder = MessageBuilder.withPayload(map)
                        .setHeader("TAGS", "bbb");
            } else if (i == 3) {
                builder = MessageBuilder.withPayload(map)
                        .setHeader("level", 1);
            } else if (i == 4) {
                builder = MessageBuilder.withPayload(map)
                        .setHeader("level", 888);
            } else {
                builder = MessageBuilder.withPayload(map);
            }

            boolean send = streamBridge.send("testchannel1-out-0", builder.build());
            System.out.println("消息" + id + "，发送状态：" + send);
        }
        return "消息发送成功！";
    }


}
