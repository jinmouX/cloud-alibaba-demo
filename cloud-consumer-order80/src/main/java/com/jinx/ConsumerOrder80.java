package com.jinx;

import com.rule.MyIRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE" ,configuration = MyIRule.class)
public class ConsumerOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrder80.class,args);
    }
}
