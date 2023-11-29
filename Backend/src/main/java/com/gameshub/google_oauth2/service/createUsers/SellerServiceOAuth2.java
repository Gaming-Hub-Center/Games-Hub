package com.gameshub.google_oauth2.service.createUsers;

import com.gameshub.Model.Users.SellerDAO;
import com.gameshub.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceOAuth2 { // TODO complete it

    @Autowired
    private SellerRepository sellerRepository;

    public void createUser(SellerDAO buyer) {
        sellerRepository.save(buyer);
    }

    public void createUser(OidcIdToken idToken) {
        int userId =  Integer.parseInt(idToken.getClaim("sub").toString());
        String name = idToken.getClaim("name").toString();
        String email = idToken.getClaim("email").toString();

        SellerDAO sellerDAO = new SellerDAO(userId, name, email, null, null, null, 0, null, null, null, null);
        sellerRepository.save(sellerDAO);
    }
}
