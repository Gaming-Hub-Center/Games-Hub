package com.gameshub.google_oauth2.controller;

import com.gameshub.Exception.ResourceNotFoundException;

import com.gameshub.google_oauth2.service.BuyerServiceOAuth2;
import com.gameshub.google_oauth2.service.SellerServiceOAuth2;
import com.gameshub.google_oauth2.service.proxy.CreateUserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;


@RestController("/oauth")
public class OAuth2Controller {

    @Autowired
    private CreateUserProxy createUserProxy;

    @Autowired
    private CreateUserProxy createSellerProxy;

    @Autowired
    private BuyerServiceOAuth2 buyerService;

    @Autowired
    private SellerServiceOAuth2 sellerService;

    @GetMapping("/buyer")
    public ResponseEntity<?> signupPageBuyer(@AuthenticationPrincipal OAuth2User oAuth2User) {
        try {
            OidcIdToken idToken = ((DefaultOidcUser) oAuth2User).getIdToken();
            if(!buyerService.emailAlreadyExist(idToken)) {
                createUserProxy.processBuyerCreation(idToken);
                return ResponseEntity.ok("Signed up successfully");
            }
            else {
                //sign in
                return ResponseEntity.ok("Logged in successfully");
            }
        } catch (ResourceNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @GetMapping("/seller")
    public ResponseEntity<?> signupPageSeller(@AuthenticationPrincipal OAuth2User principal) {
        try {
            if (principal instanceof DefaultOidcUser) {
                OidcIdToken idToken = ((DefaultOidcUser) principal).getIdToken();
                if(!sellerService.emailAlreadyExist(idToken)) {
                    createSellerProxy.processSellerCreation(idToken);
                    return ResponseEntity.ok("Signed up successfully");
                }
                else {
                    //sign in
                    return ResponseEntity.ok("Logged in successfully");
                }
            }
        } catch (ResourceNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}