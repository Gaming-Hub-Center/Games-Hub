package com.gameshub.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/google-sign-in")
    public String index(Authentication authentication) {
        ClientRegistration oktaRegistration =
                this.clientRegistrationRepository.findByRegistrationId("okta");

        System.out.println(oktaRegistration.getScopes());

        return "index";
    }

}
