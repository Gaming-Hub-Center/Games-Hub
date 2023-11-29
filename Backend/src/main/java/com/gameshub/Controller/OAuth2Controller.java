package com.gameshub.Controller;

import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.Model.Users.BuyerDAO;
import com.gameshub.Model.Users.SellerDAO;
import com.gameshub.Repository.SellerRepository;
import com.gameshub.google_oauth2.service.BuyerServiceOAuth2;
import com.gameshub.google_oauth2.service.proxy.*;
import com.gameshub.google_oauth2.service.SellerServiceOAuth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OAuth2Controller {
    @Autowired
    private SellerRepository sellerRepository;

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

    @GetMapping("/oauth/buyer")
    public ResponseEntity<?> signupPageBuyer(@AuthenticationPrincipal OAuth2User oAuth2User) {
        try {
            OidcIdToken idToken = ((DefaultOidcUser) oAuth2User).getIdToken();
            if(!buyerService.emailAlreadyExist(idToken)) {
                createBuyerProxy.processUserCreation(idToken);
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

    @GetMapping("/oauth/seller")
    public ResponseEntity<?> signupPageSeller(@AuthenticationPrincipal OAuth2User principal) {
        try {
            if (principal instanceof DefaultOidcUser) {
                OidcIdToken idToken = ((DefaultOidcUser) principal).getIdToken();
                System.out.println(idToken.getClaim("email").toString());
                if(!sellerService.emailAlreadyExist(idToken)) {
                    System.out.println("ff");
                    createSellerProxy.processUserCreation(idToken);
                    System.out.println("ffghjghj");
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

   /* @GetMapping("/booo")
    public ResponseEntity<?> booo() {
        return ResponseEntity.ok(sellerRepository.findByEmail("alice.blue@example.com").orElse(null));
    }*/

//    @GetMapping("/user/oauth")
//    public Map<String, Object> getLoginPage(@AuthenticationPrincipal OAuth2User principal) {
//        String userId = principal.getAttribute("sub");
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("id", userId);
//        String userName = principal.getAttribute("name");
//        attributes.put("name", userName);
//        String email = principal.getAttribute("email");
//        attributes.put("email", email);
//        String address = principal.getAttribute("address");
//        attributes.put("address", address);
//        return attributes;
//    }
//
//    @PostMapping("/oauth/sign-in/seller")
//    public SellerDAO signinPageSeller(@AuthenticationPrincipal OAuth2User principal) {
//        try {
//            if (principal instanceof DefaultOidcUser) {
//                OidcIdToken idToken = ((DefaultOidcUser) principal).getIdToken();
//                loginSellerProxy.processSellerLogin(idToken);
//            }
//        } catch (/*UserAlreadyExistsException | */ResourceNotFoundException | IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
//
//    @GetMapping("/oauth/sign-in/buyer")
//    public BuyerDAO signinPageBuyer(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        try {
//            if (oAuth2User instanceof DefaultOidcUser) {
//                OidcIdToken idToken = ((DefaultOidcUser) oAuth2User).getIdToken();
//                loginBuyerProxy.processBuyerLogin(idToken);
//            }
//        } catch (/*UserAlreadyExistsException | */ResourceNotFoundException | IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }

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