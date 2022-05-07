package com.uet.gts.gateway.config.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONUtil;
import com.uet.gts.common.constant.ErrorList;
import com.uet.gts.common.dto.ErrorDTO;
import com.uet.gts.common.dto.ResponseDTO;
import lombok.SneakyThrows;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;

@Component
public class CustomServerAccessDeniedHandler implements ServerAccessDeniedHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        ServerHttpResponse response= exchange.getResponse();
        response.setStatusCode(HttpStatus.FORBIDDEN);
        ErrorDTO errorDTO = new ErrorDTO(ErrorList.NO_PERMISSION);
        String body = objectMapper.writeValueAsString(new ResponseDTO(errorDTO));
        DataBuffer buffer =  response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
