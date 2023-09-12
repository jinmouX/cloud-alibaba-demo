package com.jinx.controller;

import com.jinx.common.entities.CommonResult;
import com.jinx.common.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {


    @Value("${server.port}")
    private String port;

    @PostMapping("/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        return new CommonResult(200,"请求成功 端口号：" + port +"  serial:" + payment.getSerial());
    }

}
