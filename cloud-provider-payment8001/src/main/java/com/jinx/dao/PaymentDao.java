package com.jinx.dao;

import com.jinx.common.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    int creat(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
