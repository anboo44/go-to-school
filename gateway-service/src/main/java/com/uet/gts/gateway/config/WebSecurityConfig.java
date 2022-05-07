package com.uet.gts.gateway.config;

import com.uet.gts.gateway.config.common.CustomServerAccessDeniedHandler;
import com.uet.gts.gateway.config.common.CustomServerAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Simple way: Add filter to parse JWT token. Get Role and then set it to Authentication Context
 */
@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomServerAccessDeniedHandler customServerAccessDeniedHandler;

    @Autowired
    private CustomServerAuthenticationEntryPoint customServerAuthenticationEntryPoint;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.csrf().disable()
            .cors().disable()
            .authorizeExchange()
            .pathMatchers("/auth/oauth/**").permitAll()
            .pathMatchers("/auth/**").denyAll()
            .pathMatchers("/report/**").hasAnyAuthority("admin", "manager")
            .pathMatchers(HttpMethod.DELETE, "/core/**").hasAnyAuthority("admin", "manager")
            .pathMatchers(HttpMethod.POST, "/core/**").hasAnyAuthority("admin", "manager")
            .pathMatchers(HttpMethod.PUT, "/core/**").hasAnyAuthority("admin", "manager")
            .anyExchange()
            .authenticated()
            .and().oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter())
            .and().accessDeniedHandler(customServerAccessDeniedHandler)
            .authenticationEntryPoint(customServerAuthenticationEntryPoint);
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        var grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        // Custom claim name. If not, it is "scope", "scp"
        grantedAuthoritiesConverter.setAuthoritiesClaimName("authorities");
        grantedAuthoritiesConverter.setAuthorityPrefix("");
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }

}
