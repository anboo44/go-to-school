package com.uet.gts.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HeaderFilter headerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .authorizeRequests()
            .anyRequest().permitAll();
        http.addFilterBefore(headerFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
