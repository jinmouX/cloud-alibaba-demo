package com.jinx.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jinx.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2020-01-09 16:34
 */
@RestController
@Slf4j
public class FlowLimitController
{
    @Resource
    private OrderService orderService;

    private static final String REST_URI = "http://cloudalibaba-sentinel-service2";
    @GetMapping("/testA")
    @SentinelResource(value = "testA" )
    public String testA()
    {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        return "------testB";
    }

    @GetMapping("/save")
    @SentinelResource("save")
    public String saveOrder() {
        // 查询商品
        orderService.queryGoods();
        // 查询订单
        System.err.println("新增订单");
        return "新增订单成功";
    }
    @GetMapping("/query")
    public String queryOrder() {
        // 查询商品
        orderService.queryGoods();
        // 查询订单
        System.out.println("查询订单");
        return "查询订单成功";
    }

    @GetMapping("/testD")
    @SentinelResource(value = "testD", blockHandler = "testDBlockHandler",fallback =  "testDFallback")
    public String testD()
    {
        log.info("testD 测试异常比例");
        int age = 10/0;
        return "------testD";
    }

    @GetMapping("/testE")
    @SentinelResource(value = "testE")
    public String testE()
    {
        log.info("testE 测试异常数");
        int age = 10/0;
        return "------testE";
    }

    @GetMapping("/hotKey/{id}/{name}")
    @SentinelResource(value = "hotKey")
    public String hotKey(@PathVariable("id") Long id, @PathVariable("name") String name) {
        System.out.println("热点key id: " + id + " name: " + name);
        return "id: " + id + " name: " + name;
    }

    public String testDFallback() {
        return   "------testDFallback";
    }
    public String testDBlockHandler(BlockException ex){
        return ex.getMessage()+ "------testD BlockHandler";
    }


}



