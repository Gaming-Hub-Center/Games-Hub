package com.gameshub.google_oauth2.controller;

import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.Model.Users.Buyer;
import com.gameshub.Model.Users.Seller;
import com.gameshub.google_oauth2.service.createUsers.BuyerServiceOAuth2;
import com.gameshub.google_oauth2.service.proxy.CreateBuyerProxy;
import com.gameshub.google_oauth2.service.proxy.CreateSellerProxy;
import com.gameshub.google_oauth2.service.createUsers.SellerServiceOAuth2;
import com.gameshub.google_oauth2.service.proxy.LoginBuyerProxy;
import com.gameshub.google_oauth2.service.proxy.LoginSellerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
public class OAuth2Controller {

    @Autowired
    private CreateBuyerProxy createBuyerProxy;

    @Autowired
    private CreateSellerProxy createSellerProxy;
    @Autowired
    private LoginBuyerProxy loginBuyerProxy;
    @Autowired
    private LoginSellerProxy loginSellerProxy;

    @Autowired
    private BuyerServiceOAuth2 buyerService;

    @Autowired
    private SellerServiceOAuth2 sellerService;

    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @GetMapping("/oauth/sign-up/buyer")
    public Buyer signupPageBuyer(@AuthenticationPrincipal OAuth2User oAuth2User) {
        try {
            if (oAuth2User instanceof DefaultOidcUser) {
                OidcIdToken idToken = ((DefaultOidcUser) oAuth2User).getIdToken();
                createBuyerProxy.processUserCreation(idToken);
            }
        } catch (UserAlreadyExistsException | ResourceNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @PostMapping("/oauth/sign-up/seller")
    public Seller signupPageSeller(@AuthenticationPrincipal OAuth2User principal) {
        try {
            if (principal instanceof DefaultOidcUser) {
                OidcIdToken idToken = ((DefaultOidcUser) principal).getIdToken();
                createSellerProxy.processUserCreation(idToken);
            }
        } catch (UserAlreadyExistsException | ResourceNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @PostMapping("/oauth/sign-in/seller")
    public Seller signinPageSeller(@AuthenticationPrincipal OAuth2User principal) {
        try {
            if (principal instanceof DefaultOidcUser) {
                OidcIdToken idToken = ((DefaultOidcUser) principal).getIdToken();
                loginSellerProxy.processSellerLogin(idToken);
            }
        } catch (/*UserAlreadyExistsException | */ResourceNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @GetMapping("/oauth/sign-in/buyer")
    public Buyer signinPageBuyer(@AuthenticationPrincipal OAuth2User oAuth2User) {
        try {
            if (oAuth2User instanceof DefaultOidcUser) {
                OidcIdToken idToken = ((DefaultOidcUser) oAuth2User).getIdToken();
                loginBuyerProxy.processBuyerLogin(idToken);
            }
        } catch (/*UserAlreadyExistsException | */ResourceNotFoundException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

//    @GetMapping("/create_user")
//    public Buyer createBuyer(@RequestBody Buyer buyer) {
//        return buyerService.createMockBuyer(buyer);
//    }

//    @GetMapping("login/oauth2/seller")
//    public void oauthLoginSeller(@AuthenticationPrincipal OAuth2User principal) { // TODO return the necessary data to the frontend
//        sellerService.findOrCreateUser(principal);
//    }

//    @PostMapping("/gamesHub/logout")
//    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
//        this.logoutHandler.logout(request, response, authentication);
//        return "redirect:/home";
//    }

}
