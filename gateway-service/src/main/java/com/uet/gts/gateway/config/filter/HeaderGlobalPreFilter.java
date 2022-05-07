package com.uet.gts.gateway.config.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class HeaderGlobalPreFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Can change req information by: exchange.getRequest().mutate()
        //              var newExchange = exchange.mutate().request(request).build();
        System.out.println("====[ Global Filter executed once per request if authenticated successfully ]====");
        return chain.filter(exchange);
    }
}
