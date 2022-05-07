package com.uet.gts.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class HeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.printf("====[ Header > %s%n", request.getHeader("authorization"));
        System.out.printf("====[ Header > %s%n", request.getHeader("forwarded"));
        System.out.printf("====[ Header > path: %s%n", request.getRequestURI());
        filterChain.doFilter(request, response);
    }
}
