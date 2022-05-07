package com.uet.gts.auth.config;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final String START_TIME_KEY = "startTime";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_KEY, startTime);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        long startTime = (Long) request.getAttribute(START_TIME_KEY);
        long endTime = System.currentTimeMillis();
        System.out.println("====[ Time Taken: " + (endTime - startTime) + "ms ]====");
    }
}
