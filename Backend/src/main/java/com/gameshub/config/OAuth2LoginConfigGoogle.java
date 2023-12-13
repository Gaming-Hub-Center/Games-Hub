//package com.gameshub.config;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.context.annotation.*;
//import org.springframework.security.config.annotation.web.configuration.*;
//import org.springframework.security.oauth2.client.registration.*;
//import org.springframework.security.oauth2.core.*;
//import org.springframework.security.oauth2.core.oidc.*;
//
////@Configuration
//@EnableWebSecurity
//public class OAuth2LoginConfigGoogle {
//
//    @Value("${google.client-id}")
//    private String clientId;
//
//    @Value("${google.client-secret}")
//    private String clientSecret;
//
//    @Value("${google.authorization-uri}")
//    private String authorizationUri;
//
//    @Value("${google.redirect-uri}")
//    private String redirectUri;
//
//    @Value("${google.token-uri}")
//    private String tokenUri;
//
//    @Value("${google.user-info-uri}")
//    private String userInfoUri;
//
//    @Value("${google.jwk-set-uri}")
//    private String jwkSetUri;
//
//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
//    }
//
//    private ClientRegistration googleClientRegistration() {
//        return ClientRegistration.withRegistrationId("google")
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri(redirectUri)
//                .scope("openid", "profile", "email")
//                .authorizationUri(authorizationUri)
//                .tokenUri(tokenUri)
//                .userInfoUri(userInfoUri)
//                .userNameAttributeName(IdTokenClaimNames.SUB)
//                .jwkSetUri(jwkSetUri)
//                .clientName("Google")
//                .build();
//    }
//
//}