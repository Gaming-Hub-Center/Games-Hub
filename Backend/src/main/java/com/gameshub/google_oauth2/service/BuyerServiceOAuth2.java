package com.gameshub.google_oauth2.service;

import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.Model.Users.BuyerDAO;
import com.gameshub.Model.Users.SellerDAO;
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

    public void createUser(BuyerDAO buyer) {
        buyerRepository.save(buyer);
    }

    public void createUser(OidcIdToken idToken) {
        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());
        String name = idToken.getClaim("name").toString();
        String email = idToken.getClaim("email").toString();
        BuyerDAO buyer = new BuyerDAO(userId, name, email, null, null, null, 0);

        buyerRepository.save(buyer);
    }

    public boolean emailAlreadyExist(OidcIdToken idToken) {
        String email = idToken.getClaim("email").toString();
        BuyerDAO buyer = buyerRepository.findByEmail(email);
        if(buyer != null) {
            return true;
        }
        return false;
    }

}
