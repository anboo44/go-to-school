package com.uet.gts.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableCaching
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer //REMEMBER: Enable this => call API using accessToken.
public class AuthApplication {
    // This service is:
    //                  Authentication Server => login & invoke accessToken
    //                  ResourceServer        => using accessToken to validate api
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
