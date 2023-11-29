package com.gameshub.google_oauth2.service.proxy;

import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.Model.Users.BuyerDAO;
import com.gameshub.Repository.BuyerRepository;
import com.gameshub.google_oauth2.service.createUsers.BuyerServiceOAuth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Component;

@Component
public class CreateBuyerProxy {

    @Autowired
    private BuyerServiceOAuth2 buyerService;

    @Autowired
    private BuyerRepository buyerRepository;

    public void processUserCreation(OidcIdToken idToken) {
        if (idToken == null) {
            throw new IllegalArgumentException("OAuth2User cannot be null");
        }

        String user = idToken.getClaim("sub");
        String email = idToken.getClaim("email").toString();


        if (user == null) {
            throw new ResourceNotFoundException("id is missing");
        }

        BuyerDAO buyerDAO = buyerRepository.findByEmail(email);

        if(email != null) {
            throw new UserAlreadyExistsException("User Exists");
        }

        // Check for other required attributes
        checkAttribute(idToken, "name");
        checkAttribute(idToken, "sub");

        buyerService.createUser(idToken);
    }

    private void checkAttribute(OidcIdToken oAuth2User, String attributeName) {
        if (oAuth2User.getClaim(attributeName) == null) {
            throw new ResourceNotFoundException("Missing attribute: " + attributeName);
        }
    }
}