package com.jinx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignConsumerMain80
{
    public static void main( String[] args )
    {
        SpringApplication.run(FeignConsumerMain80.class,args);
    }
}
