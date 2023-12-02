package com.gameshub.Controller;

import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Service.BuyerServiceOAuth2;
import com.gameshub.Service.SellerServiceOAuth2;
import com.gameshub.Service.CreateUserProxy;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller implements ErrorController {

    @Autowired
    private CreateUserProxy createUserProxy;

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
            OidcIdToken idToken = ((DefaultOidcUser) principal).getIdToken();
            if(!sellerService.emailAlreadyExist(idToken)) {
                createUserProxy.processSellerCreation(idToken);
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

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
            String message = (String) request.getSession().getAttribute("error.message");
            request.getSession().removeAttribute("error.message");
            return message;
        }
        return "error";
    }


}