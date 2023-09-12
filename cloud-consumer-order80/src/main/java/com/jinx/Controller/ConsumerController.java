package com.jinx.Controller;

import com.jinx.common.entities.CommonResult;
import com.jinx.common.entities.Payment;
import com.jinx.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalance loadBalance;

    public static final String EUREKA_URL = "http://CLOUD-PAYMENT-SERVICE";
    //public static final String EUREKA_URL = "http://localhost:7001";
    @GetMapping("/consumer/payment/creat")
    public CommonResult consumerCreat(Payment payment){
        log.info("请求参数 {}",payment);
        return restTemplate.postForEntity(EUREKA_URL+"/payment/creat",payment,CommonResult.class).getBody();
    }

    @GetMapping("/consumer/payment/creat1")
    public CommonResult consumerCreat1(Payment payment){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance serviceInstance = loadBalance.instance(instances);
        String url = serviceInstance.getUri().toString();
        log.info("请求地址 {}",url);
        return restTemplate.postForEntity(url+"/payment/creat",payment,CommonResult.class).getBody();
    }
}
