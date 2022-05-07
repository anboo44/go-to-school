package com.uet.gts.auth.config;

import com.uet.gts.auth.model.dto.UserDetailDTO;
import com.uet.gts.auth.service.OAuth2ClientDetailsService;
import com.uet.gts.common.constant.KeyConstant;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .reuseRefreshTokens(true)
 * 1. Reuse: When the access_token expires and refreshes, the refresh token expiration time does not change, and the time when it was first generated shall prevail.
 * 2. Non-reuse: When the access_token expires and refreshes, the refresh_token expiration time continues, and the refresh_token is refreshed within the validity period of the refresh_token without the need to log in again.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OAuthResourceConfig resource;

    @Autowired
    private OAuth2ClientDetailsService oAuth2ClientService;

    @Autowired
    private UserDetailsService userDetailsService;

    //===============[ Main functions ]======================
    //------- All other fields & functions to support these main functions ---------
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @SneakyThrows
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(oAuth2ClientService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(tokenEnhancer());
        tokenEnhancers.add(jwtAccessTokenConverter());
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);

        endpoints.authenticationManager(authenticationManager)
                 .userDetailsService(userDetailsService)
                 .tokenEnhancer(tokenEnhancerChain)
                 .reuseRefreshTokens(true)
                 .exceptionTranslator(loggingExceptionTranslator());
    }

    //===============[ Support functions ]======================
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            Map<String, Object> map = new HashMap<>(1);
            UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getUserAuthentication().getPrincipal();
            map.put(KeyConstant.USER_ID, userDetailDTO.getUser().getId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(map);
            return accessToken;
        };
    }

    @SneakyThrows
    private JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyPair());
        return converter;
    }

    @Bean
    public KeyPair keyPair() {
        // Get the key pair from the certificate jwt.jks in the classpath directory
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
                new ClassPathResource(resource.getKeyStore()), resource.getKeyPairPassword().toCharArray()
        );
        return keyStoreKeyFactory.getKeyPair(
                resource.getKeyPairAlias(),
                resource.getKeyPairPassword().toCharArray()
        );
    }

    private WebResponseExceptionTranslator loggingExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                // e.printStackTrace();
                ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                OAuth2Exception excBody = responseEntity.getBody();
                return new ResponseEntity<>(excBody, headers, responseEntity.getStatusCode());
            }
        };
    }
}
