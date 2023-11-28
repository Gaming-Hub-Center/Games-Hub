package com.gameshub.google_oauth2.service;

import com.gameshub.Model.Users.Buyer;
import com.gameshub.Repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public void findOrCreateUser(OAuth2User oauthUser) {
        String userId = oauthUser.getAttribute("sub");
        String name = oauthUser.getAttribute("name");
        String email = oauthUser.getAttribute("email");

        // Check if user already exists
        Buyer existingUser = buyerRepository.findByEmail(email);
        if (existingUser != null) {
            return;
        }

        // If not, create a new user
        Buyer newUser = new Buyer();
        newUser.setName(oauthUser.getAttribute("name"));
        newUser.setEmail(email);
        newUser.setName(name);
        assert userId != null;
        newUser.setId(Integer.parseInt(userId));
        newUser.setBalance(0);

        buyerRepository.save(newUser);
    }
}
