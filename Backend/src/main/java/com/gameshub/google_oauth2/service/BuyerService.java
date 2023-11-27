package com.gameshub.google_oauth2.service;

import com.gameshub.Model.Users.Buyer;
import com.gameshub.Repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerService { // TODO complete it

    @Autowired
    private BuyerRepository buyerRepository;

    public Buyer findOrCreateUser(String email, String name) {
        return new Buyer();
    }
}
