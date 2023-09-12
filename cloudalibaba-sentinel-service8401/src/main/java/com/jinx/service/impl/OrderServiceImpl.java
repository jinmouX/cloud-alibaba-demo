package com.jinx.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.jinx.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {


    @SentinelResource("goods")
    public void queryGoods(){
        System.err.println("查询商品");
    }
}
