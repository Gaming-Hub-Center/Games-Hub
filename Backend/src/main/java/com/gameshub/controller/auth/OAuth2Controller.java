package com.gameshub.controller.auth;

import com.gameshub.exception.*;
import com.gameshub.service.user.BuyerServiceOAuth2;
import com.gameshub.service.user.CreateUserProxy;
import com.gameshub.service.user.SellerServiceOAuth2;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.springframework.boot.web.servlet.error.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.oauth2.core.oidc.*;
import org.springframework.security.oauth2.core.oidc.user.*;
import org.springframework.security.oauth2.core.user.*;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller implements ErrorController {

    private final CreateUserProxy createUserProxy;

    private final BuyerServiceOAuth2 buyerService;

    private final SellerServiceOAuth2 sellerService;

    @GetMapping("/buyer")
    public ResponseEntity<?> signupPageBuyer(@AuthenticationPrincipal OAuth2User oAuth2User) {
        try {
            OidcIdToken idToken = ((DefaultOidcUser) oAuth2User).getIdToken();
            System.out.println(idToken.getTokenValue());
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
