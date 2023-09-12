package com.jinx.Controller;

import com.jinx.openfeign.FeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class ConsumerController {

    @Resource
    private FeignService feignService;

    @GetMapping("/consumer/payment/feign/{id}")
    public String feignServer(@PathVariable("id") Long id){
        return feignService.feignServer(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeOut(){
        return feignService.paymentFeignTimeOut();
    }


}
