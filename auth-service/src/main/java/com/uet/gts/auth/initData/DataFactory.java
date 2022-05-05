package com.uet.gts.auth.initData;

import com.uet.gts.auth.model.entity.OAuth2Client;
import com.uet.gts.auth.model.entity.User;
import com.uet.gts.auth.model.entity.UserAuthority;
import com.uet.gts.auth.model.enumeration.UserRole;
import com.uet.gts.auth.repository.OAuth2ClientRepository;
import com.uet.gts.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataFactory {

    @Autowired
    private OAuth2ClientRepository oAuth2ClientRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initOAuth2ClientData() {
        var clients = oAuth2ClientRepository.findAll();
        if (clients.isEmpty()) {
            oAuth2ClientRepository.saveAllAndFlush(getOAuth2ClientDummy());
        }
    }

    @PostConstruct
    public void initUserData() {
        var users = userRepository.findAll();
        if (users.isEmpty()) {
            userRepository.saveAllAndFlush(getUserDummy());
        }
    }

    private List<User> getUserDummy() {
        var normalAuthority = UserAuthority.builder()
                .userRole(UserRole.ROLE_USER)
                .build();

        var user1 = User.builder()
                .username("anboo33")
                .password("Aa@123456")
                .build();
        normalAuthority.setUser(user1);
        user1.setUserAuthority(normalAuthority);

        return List.of(user1);
    }

    private List<OAuth2Client> getOAuth2ClientDummy() {
        var client = OAuth2Client.builder()
                .clientId("web-client")
                .clientSecret("Aa@123456")
                .scope("read,write")
                .authorizedGrantType("password,refresh_token,client_credentials,authorization_code,implicit")
                .callbackUrl("https://google.vn")
                .memo("Client Demo")
                .build();
        return List.of(client);
    }
}
