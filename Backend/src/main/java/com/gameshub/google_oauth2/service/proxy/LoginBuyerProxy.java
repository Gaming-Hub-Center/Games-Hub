package com.gameshub.google_oauth2.service.proxy;


import com.gameshub.google_oauth2.service.BuyerServiceOAuth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Component;

@Component
public class LoginBuyerProxy {
    @Autowired
    private BuyerServiceOAuth2 buyerService;
    public void processBuyerLogin(OidcIdToken idToken) {
        if (idToken == null) {
            throw new IllegalArgumentException("OAuth2User cannot be null");
        }
    }
}