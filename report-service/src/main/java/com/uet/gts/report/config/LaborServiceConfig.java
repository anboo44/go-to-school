package com.uet.gts.report.config;

import com.uet.gts.common.exception.ExternalApiCallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class LaborServiceConfig {

    @Autowired
    private DiscoveryClient discoveryClient;

    private final static String CORE_SERVICE_NAME = "gts-core-service";
    private final static String GATEWAY_SERVICE_NAME = "gts-gateway-service";
    private final static int FIRST_IDX = 0;
    private final static String PROTOCOL = "http";

    public LaborServiceConfig() {
        this.timer();
    }

    public String getGatewayPath() {
        return getBasePath(GATEWAY_SERVICE_NAME);
    }

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
    public void clearInstanceCache() {
        // Reset `randomInstance` every 5 minutes
        timer();
    }

    private void timer() {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5 * 60 * 1000); // 5 minutes
                clearInstanceCache();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
