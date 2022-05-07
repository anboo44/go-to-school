package com.uet.gts.gateway.config.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uet.gts.common.constant.ErrorList;
import com.uet.gts.common.dto.ErrorDTO;
import com.uet.gts.common.dto.ResponseDTO;
import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class CustomServerAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        ErrorDTO errorDTO = new ErrorDTO(ErrorList.UN_AUTHORIZED);
        String body = objectMapper.writeValueAsString(new ResponseDTO(errorDTO));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
