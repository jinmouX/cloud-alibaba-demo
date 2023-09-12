package com.jinx.lb.imp;

import com.jinx.lb.LoadBalance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalanceImpl implements LoadBalance {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = atomicInteger.get();
            next = current > Integer.MAX_VALUE ? 0 : current+1;
        }while (!atomicInteger.compareAndSet(current,next));
        return next;
    }
    @Override
    public ServiceInstance instance(List<ServiceInstance> instanceList) {
        int index = getAndIncrement() % instanceList.size();
        return instanceList.get(index);
    }
}
