package com.jinx.service.impl;

import com.jinx.common.entities.CommonResult;
import com.jinx.common.entities.Payment;
import com.jinx.dao.PaymentDao;
import com.jinx.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Value("${server.port}")
    private String port;
    public CommonResult creat(Payment payment) {
        int creat = paymentDao.creat(payment);
        if(creat > 0){
            return new CommonResult(200,"插入成功+id:" + payment.getId() +"端口为:" + port);
        }else {
            return new CommonResult(444,"插入失败");
        }
    }

    public CommonResult getPaymentById(Long id) {
        Payment payment = paymentDao.getPaymentById(id);
        if(payment !=null){
            return  new CommonResult(200,"查询成功",payment);
        }else {
            return  new CommonResult(444,"查询失败");
        }
    }
}
