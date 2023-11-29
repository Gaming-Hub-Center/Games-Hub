package com.gameshub.google_oauth2.service;

import com.gameshub.Model.Users.DAOs.SellerDAO;
import com.gameshub.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceOAuth2 { // TODO complete it

    @Autowired
    private SellerRepository sellerRepository;

    public void createUser(OidcIdToken idToken) {
        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());
        String name = idToken.getClaim("name").toString();
        String email = idToken.getClaim("email").toString();

        SellerDAO sellerDAO = new SellerDAO(userId, name, email, null, null, null, 0, null, null, null, null);
        sellerRepository.saveAndFlush(sellerDAO);
    }

    public boolean emailAlreadyExist(OidcIdToken idToken) {
        String email = idToken.getClaim("email").toString();
        SellerDAO sellerDAO = sellerRepository.findByEmail(email).orElse(null);
        if(sellerDAO != null) {
            return true;
        }
        return false;
    }
}