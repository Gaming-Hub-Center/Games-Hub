package com.gameshub.Service;

import com.gameshub.Exception.*;
import com.gameshub.Model.Users.DAOs.SellerDAO;
import com.gameshub.Repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SellerDetailsService implements UserDetailsService {

    @Autowired
    private SellerRepository sellerRepository;

    public List<SellerDAO> getAll() {
        return sellerRepository.findAll();
    }

    public SellerDAO getByEmail(String email) {
        return sellerRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws ResourceNotFoundException {
        SellerDAO sellerDAO = sellerRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer not found with email: " + username));

        return new User(sellerDAO.getEmail(), sellerDAO.getPassword(), new ArrayList<>());
    }

    public Boolean userExists(String email){
        return sellerRepository.existsByEmail(email);
    }

    public void saveNewUser(SellerDAO seller){
        sellerRepository.save(seller);
    }

}
