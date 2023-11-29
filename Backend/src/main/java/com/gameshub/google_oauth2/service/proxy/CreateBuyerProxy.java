package com.gameshub.google_oauth2.service.proxy;

import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.google_oauth2.service.BuyerServiceOAuth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Component;

@Component
public class CreateBuyerProxy {

    @Autowired
    private BuyerServiceOAuth2 buyerService;

    public void processUserCreation(OidcIdToken idToken) {
        if (idToken == null) {
            throw new IllegalArgumentException("OAuth2User cannot be null");
        }

        String user = idToken.getClaim("sub");
        String email = idToken.getClaim("email").toString();

        if (user == null) {
            throw new ResourceNotFoundException("id is missing");
        }

        if(email != null) {
            throw new UserAlreadyExistsException("User Exists");
        }

        // Check for other required attributes
        checkAttribute(idToken, "name");
        checkAttribute(idToken, "sub");

//        Buyer buyer = new Buyer(userId, name, email, null, null, null, 0);
        buyerService.createUser(idToken);
    }

    private void checkAttribute(OidcIdToken oAuth2User, String attributeName) {
        if (oAuth2User.getClaim(attributeName) == null) {
            throw new ResourceNotFoundException("Missing attribute: " + attributeName);
        }
    }
}