package com.uet.gts.report.config;

import com.uet.gts.common.exception.ExternalApiCallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class LaborServiceConfig {

    @Autowired
    private DiscoveryClient discoveryClient;

    private final static String CORE_SERVICE_NAME = "gts-core-service";
    private final static int FIRST_IDX = 0;
    private final static String PROTOCOL = "http";

    public String getCorePath() {
        return getBasePath(CORE_SERVICE_NAME);
    }

    private String getBasePath(String targetServiceName) {
        var ins = randomInstance(targetServiceName);
        if (ins == null) throw new ExternalApiCallException(503, targetServiceName);
        return String.format("%s://%s:%s", PROTOCOL, ins.getHost(), ins.getPort());
    }

    @Cacheable(value = "randomInstance", unless = "#result == null")
    public ServiceInstance randomInstance(String targetServiceName) {
        var instances = discoveryClient.getInstances(targetServiceName);
        if (instances.isEmpty()) {
            return null;
        } else {
            return instances.get(FIRST_IDX);
        }
    }

    @CacheEvict(value = "randomInstance", allEntries = true)
    public void clearInstanceCache() {}
}
