package com.gameshub.google_oauth2.service.createUsers;

import com.gameshub.Model.Users.BuyerDAO;
import com.gameshub.Repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceOAuth2 {

    @Autowired
    private BuyerRepository buyerRepository;

    public void createUser(BuyerDAO buyer) {
        buyerRepository.save(buyer);
    }

    public void createUser(OidcIdToken idToken) {
        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());
        String name = idToken.getClaim("name").toString();
        String email = idToken.getClaim("email").toString();
        BuyerDAO buyerDAO = new BuyerDAO(userId, name, email, null, null, null, 0);

        buyerRepository.save(buyerDAO);
    }

}
