package com.gameshub.Service;

import com.gameshub.Model.Users.*;
import com.gameshub.Repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BuyerDetailsService implements UserDetailsService {

    @Autowired
    private BuyerRepository buyerRepository;

    public List<BuyerDAO> getAll() {
        return buyerRepository.findAll();
    }

    public BuyerDAO getByEmail(String email) {
        return buyerRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BuyerDAO buyerDAO = buyerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Buyer not found with email: " + username));

        return new User(buyerDAO.getEmail(), buyerDAO.getPassword(), new ArrayList<>());
    }

}
