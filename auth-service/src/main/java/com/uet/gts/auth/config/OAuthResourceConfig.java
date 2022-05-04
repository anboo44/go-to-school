package com.uet.gts.auth.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class OAuthResourceConfig {

    @Value("${security.jwt.key.store}")
    private String keyStore;

    @Value("${security.jwt.key.store-password}")
    private String keyStorePassword;

    @Value("${security.jwt.key.pair-alias}")
    private String keyPairAlias;

    @Value("${security.jwt.key.pair-password}")
    private String keyPairPassword;

}
