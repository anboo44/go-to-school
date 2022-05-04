package com.uet.gts.report.config;

import com.uet.gts.common.exception.ExternalApiCallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

@Component
public class LaborServiceConfig {

    @Autowired
    private DiscoveryClient discoveryClient;

    private final static String CORE_SERVICE_NAME = "gts-core-service";
    private final static String GATEWAY_SERVICE_NAME = "gts-gateway-service";
    private final static int FIRST_IDX = 0;
    private final static String PROTOCOL = "http";

    public String getGatewayPath() {
        return getBasePath(GATEWAY_SERVICE_NAME);
    }

    public String getCorePath() {
        return getBasePath(CORE_SERVICE_NAME);
    }

    private String getBasePath(String targetServiceName) {
        var ins = randomInstance(targetServiceName);
        return String.format("%s://%s:%s", PROTOCOL, ins.getHost(), ins.getPort());
    }

    // TODO: Cache instance in 5 minutes. After, refresh instance by random instead of fixed at head
    private ServiceInstance randomInstance(String targetServiceName) {
        var instances = discoveryClient.getInstances(targetServiceName);
        if (instances.isEmpty()) {
            throw new ExternalApiCallException(503, targetServiceName);
        } else {
            return instances.get(FIRST_IDX);
        }
    }
}
