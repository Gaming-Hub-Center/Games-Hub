package com.gameshub.google_oauth2.controller;

import com.gameshub.google_oauth2.service.BuyerService;
import com.gameshub.google_oauth2.service.SellerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    @GetMapping("login/oauth2/buyer")
    public void oauthLoginBuyer(@AuthenticationPrincipal OAuth2User principal) { // TODO return the necessary data to the frontend
        buyerService.findOrCreateUser(principal);
    }

    @GetMapping("login/oauth2/seller")
    public void oauthLoginSeller(@AuthenticationPrincipal OAuth2User principal) { // TODO return the necessary data to the frontend
        sellerService.findOrCreateUser(principal);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {  // TODO test and get the correct action and connect to the front
        SecurityContextHolder.clearContext();
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
