package com.gameshub.google_oauth2.service.createUsers;

import com.gameshub.Model.Users.Buyer;
import com.gameshub.Model.Users.Seller;
import com.gameshub.Repository.BuyerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceOAuth2 {

    @Autowired
    private BuyerRepository buyerRepository;

    public void createUser(Buyer buyer) {
        buyerRepository.save(buyer);
    }

    public void createUser(OidcIdToken idToken) {
        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());
        String name = idToken.getClaim("name").toString();
        String email = idToken.getClaim("email").toString();
        Buyer buyer = new Buyer(userId, name, email, null, null, null, 0);

        buyerRepository.save(buyer);
    }

}
