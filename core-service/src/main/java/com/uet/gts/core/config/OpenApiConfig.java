package com.uet.gts.core.config;

import com.google.common.collect.Lists;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(Lists.newArrayList(
                        new Server().url("http://localhost:8711/api").description("Local Envi")
                ))
                .info(new Info().title("Core-Service API Document")
                        .contact(new Contact()
                                .email("hungpt58.uet@gmail.com")
                                .name("Storm Spirit")
                                .url("http://localhost:8711/swagger-ui/index.htm"))
                        .version("1.0.0"));
    }
}
