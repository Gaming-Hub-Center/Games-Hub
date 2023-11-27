package com.gameshub.google_oauth2.controller;

import com.gameshub.google_oauth2.service.BuyerService;
import com.gameshub.google_oauth2.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuth2Controller {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("/user/oauth")
    public Map<String, Object> getLoginPage(@AuthenticationPrincipal OAuth2User principal) {
        String userId = principal.getAttribute("sub");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", userId);
        String userName = principal.getAttribute("name");
        attributes.put("name", userName);
        String email = principal.getAttribute("email");
        attributes.put("email", email);
        String address = principal.getAttribute("address");
        attributes.put("address", address);
        return attributes;
    }
}
