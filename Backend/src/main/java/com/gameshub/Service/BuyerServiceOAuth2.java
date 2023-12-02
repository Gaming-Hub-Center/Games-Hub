package com.gameshub.Service;

import com.gameshub.Model.Users.DAOs.BuyerDAO;
import com.gameshub.Repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceOAuth2 {

    @Autowired
    private BuyerRepository buyerRepository;

    public void createUser(OidcIdToken idToken) {
        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());
        String name = idToken.getClaim("name").toString();
        String email = idToken.getClaim("email").toString();
        BuyerDAO buyer = new BuyerDAO(userId, name, email, null, null, null, 0);

        buyerRepository.saveAndFlush(buyer);
    }

    public boolean emailAlreadyExist(OidcIdToken idToken) {
        String email = idToken.getClaim("email").toString();
        BuyerDAO buyer = buyerRepository.findByEmail(email).orElse(null);
        if(buyer != null) {
            return true;
        }
        return false;
    }

}