package com.jinx.controller;

import com.jinx.common.entities.CommonResult;
import com.jinx.common.entities.Payment;
import com.jinx.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;


    @PostMapping("/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        return paymentService.creat(payment);
    }

    @GetMapping("/payment/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        return paymentService.getPaymentById(id);
    }

    @GetMapping("/payment/feign/{id}")
    public String feignServer(@PathVariable("id") Long id){

        return "当前线程:" +  Thread.currentThread().getName()+"请求id :"+ + id + "请求端口号:" + port ;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
        System.out.println("*****paymentFeignTimeOut from port: "+port);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return port;
    }
}
