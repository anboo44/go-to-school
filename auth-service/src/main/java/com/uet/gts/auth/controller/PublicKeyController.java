package com.uet.gts.auth.controller;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
public class PublicKeyController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey)
                .keyUse(KeyUse.SIGNATURE)
                .keyID("gts-auth-id")
                .algorithm(JWSAlgorithm.RS256)
                .build();
        return new JWKSet(key).toJSONObject();
    }
}
