package com.gameshub.google_oauth2.service.proxy;

import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.google_oauth2.service.SellerServiceOAuth2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Component;

@Component
public class CreateSellerProxy {

    @Autowired
    private SellerServiceOAuth2 sellerService;

    public void processUserCreation(OidcIdToken idToken) {
        if (idToken == null) {
            throw new IllegalArgumentException("OAuth2User cannot be null");
        }

        String user = idToken.getClaim("sub");
//        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());///////////////////////////
        String name = idToken.getClaim("name").toString();
        String email = idToken.getClaim("email").toString();

        if (user == null) {
            throw new ResourceNotFoundException("id is missing");
        }

        /*if(email != null) {
            throw new UserAlreadyExistsException("User Exists");
        }*/

        // Check for other required attributes
        checkAttribute(idToken, "name");
        checkAttribute(idToken, "sub");

//        Seller seller = new Seller(userId, name, email, null, null, null, 0, null, null, null, null);

        sellerService.createUser(idToken);
        //return retreive page
    }

    private void checkAttribute(OidcIdToken idToken, String attributeName) {
        if (idToken.getClaim(attributeName) == null) {
            throw new ResourceNotFoundException("Missing attribute: " + attributeName);
        }
    }


}