package com.uet.gts.auth.service.impl;

import com.uet.gts.auth.repository.OAuth2ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OAuth2ClientDetailsService implements ClientDetailsService {

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        var clientOpt = oAuth2ClientRepository.findById(clientId);
        if (clientOpt.isEmpty()) {
            throw new ClientRegistrationException("Not found client: " + clientId);
        }

        var client = clientOpt.get();
        var scopes = client.getScope().split(",");
        var authorizedGrantTypes = client.getAuthorizedGrantType().split(",");
        var callbackUrls = client.getCallbackUrl().split(",");

        var basicClientDetail = new BaseClientDetails();
        basicClientDetail.setClientId(clientId);
        basicClientDetail.setClientSecret(client.getClientSecret());
        basicClientDetail.setScope(Set.of(scopes));
        basicClientDetail.setAuthorizedGrantTypes(Set.of(authorizedGrantTypes));
        basicClientDetail.setRegisteredRedirectUri(Set.of(callbackUrls));
        basicClientDetail.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        basicClientDetail.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        basicClientDetail.setResourceIds(List.of("oauth2-resource"));

        return basicClientDetail;
    }
}
