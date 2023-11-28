package com.gameshub.google_oauth2.controller;

import com.gameshub.google_oauth2.service.BuyerService;
import com.gameshub.google_oauth2.service.SellerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuth2Controller {

    @Autowired
    private BuyerService buyerService;

    @Autowired
    private SellerService sellerService;

    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping("/oauth/user")
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
//    @GetMapping("login/oauth2/seller")
//    public void oauthLoginSeller(@AuthenticationPrincipal OAuth2User principal) { // TODO return the necessary data to the frontend
//        sellerService.findOrCreateUser(principal);
//    }

    @PostMapping("/gamesHub/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/home";
    }

}
