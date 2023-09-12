package com.jinx.Controller;

import com.jinx.common.entities.CommonResult;
import com.jinx.common.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;

    public static final String EUREKA_URL = "http://zk-payment-service";
    //public static final String EUREKA_URL = "http://localhost:7001";
    @GetMapping("/consumer/payment/creat")
    public CommonResult consumerCreat(Payment payment){
        log.info("请求参数 {}",payment);
        return restTemplate.postForEntity(EUREKA_URL+"/payment/creat",payment,CommonResult.class).getBody();
    }
}
