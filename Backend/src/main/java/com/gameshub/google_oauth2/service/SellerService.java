package com.gameshub.google_oauth2.service;

import com.gameshub.Model.Users.Seller;
import com.gameshub.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService { // TODO complete it

    @Autowired
    private SellerRepository sellerRepository;

    public Seller findOrCreateUser(String email, String name) {
        return new Seller();
    }
}
