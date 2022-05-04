package com.uet.gts.auth.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "oauth2_client")
public class OAuth2Client implements Serializable {
    private static final long serialVersionUID = -1122009681459879159L;

    @Id
    @Column(name = "clientId", nullable = false, length = 100)
    private String clientId;

    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    //TODO: Using enum: READ, WRITE
    @Column(name = "scope", length = 100, nullable = false)
    private String scope;

    //TODO: Using enum: password,refresh_token,client_credentials
    @Column(name = "authorized_grant_type", nullable = false)
    private String authorizedGrantType;

    @Column(name = "callback_url")
    private String callbackUrl;

    @Column(name = "access_token_validity", nullable = false)
    @Builder.Default
    private Integer accessTokenValidity = 30 * 60; // 30 minutes. unit: seconds

    @Column(name = "refresh_token_validity", nullable = false)
    @Builder.Default
    private Integer refreshTokenValidity = 7 * 24 * 60 * 60; // 7 days. unit: seconds

    @Column(name = "memo")
    private String memo;
}
