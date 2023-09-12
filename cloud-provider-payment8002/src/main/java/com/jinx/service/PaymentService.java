package com.jinx.service;

import com.jinx.common.entities.CommonResult;
import com.jinx.common.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    CommonResult creat(Payment payment);

    CommonResult getPaymentById(@Param("id") Long id);
}
