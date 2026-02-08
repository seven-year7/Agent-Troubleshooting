package com.wuxi.infinitechat.authenticationservice.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.wuxi.infinitechat.authenticationservice.loadBalancer.UrlHashLoadBalancer;
import com.wuxi.infinitechat.authenticationservice.model.enums.ConfigEnum;
import com.wuxi.infinitechat.authenticationservice.model.enums.TimeOutEnum;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceInstanceUtil {

    private final DiscoveryClient discoveryClient;

    private final StringRedisTemplate redisTemplate;

    public String getServiceInstance(String userId){
        List<ServiceInstance> instances = discoveryClient.getInstances(ConfigEnum.NETTY_SERVER.getValue());
        if (instances.size() == 0) {
            return null;
        }
        UrlHashLoadBalancer urlHashLoadBalancer = new UrlHashLoadBalancer();
        ServiceInstance instance = urlHashLoadBalancer.select(instances, userId);
        redisTemplate.opsForValue().set(ConfigEnum.NETTY_SERVER_HEAD.getValue() + userId, instance.getUri().toString(), TimeOutEnum.TOKEN_TIME_OUT.getTimeOut(), TimeUnit.DAYS);
        return instance.getHost();
    }
}
