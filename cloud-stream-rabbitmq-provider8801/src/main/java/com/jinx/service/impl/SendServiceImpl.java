package com.jinx.service.impl;

import com.jinx.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


@EnableBinding(Source.class)
@Slf4j
public class SendServiceImpl implements SendService {
    @Resource
    private MessageChannel output;
    @Override
    public void send() {
        String uuid = UUID.randomUUID().toString();
        log.info("发送消息到消息发送器:" + uuid);
        this.output.send(MessageBuilder.withPayload(uuid).build());
    }
}
