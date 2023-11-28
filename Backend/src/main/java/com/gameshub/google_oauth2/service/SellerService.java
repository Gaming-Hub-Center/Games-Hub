package com.gameshub.google_oauth2.service;

import com.gameshub.Model.Users.Buyer;
import com.gameshub.Model.Users.Seller;
import com.gameshub.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class SellerService { // TODO complete it

    @Autowired
    private SellerRepository sellerRepository;

    public void findOrCreateUser(OAuth2User oauthUser) {
        String userId = oauthUser.getAttribute("sub");
        String name = oauthUser.getAttribute("name");
        String email = oauthUser.getAttribute("email");

        // Check if user already exists
        Seller existingUser = sellerRepository.findByEmail(email);
        if (existingUser != null) {
            return;
        }

        // If not, create a new user
        Seller newUser = new Seller();
        newUser.setName(oauthUser.getAttribute("name"));
        newUser.setEmail(email);
        newUser.setName(name);
        assert userId != null;
        newUser.setId(Integer.parseInt(userId));
        newUser.setBalance(0);

        sellerRepository.save(newUser);
    }
}
