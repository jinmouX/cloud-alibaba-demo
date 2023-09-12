package com.jinx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class FeignHystrixMain80
{
    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixMain80.class,args);
    }
}
