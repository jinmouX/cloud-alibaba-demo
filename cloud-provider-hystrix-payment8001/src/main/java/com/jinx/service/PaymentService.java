package com.jinx.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@DefaultProperties(defaultFallback = "defaultFallback")
public class PaymentService
{
    /**
     * 正常访问，一切OK
     * @param id
     * @return
     */
    @HystrixCommand
    public String paymentInfo_OK(Integer id)
    {
        int i = 10/0;
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O";
    }

    /**
     * 超时访问，演示降级
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfoFallback",
            commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")}
    )
    public String paymentInfo_TimeOut(Integer id)
    {
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池:"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id: "+id+"\t"+"O(∩_∩)O，耗费3秒";
    }
    public String paymentInfoFallback(Integer id) throws InterruptedException
    {
        return "paymentInfo_TimeOut访问出了点小问题,请重试";
    }

    public String defaultFallback() throws InterruptedException
    {
        return "访问出了点小问题,请重试";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
                    @HystrixProperty(name = "circuitBreaker.enabled" ,value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" ,value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
            }
    )
    public String getNum(Integer num) {
        if(num < 0 ){
            throw new RuntimeException("不能是负数");
        }
        return "结果为：" + num ;
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}

