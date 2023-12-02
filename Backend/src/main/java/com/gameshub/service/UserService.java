package com.gameshub.service;

import com.gameshub.exception.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    public List<UserDAO> getAllUsers() {
        List<BuyerDAO> buyerDAOS = buyerRepository.findAll();
        List<SellerDAO> sellerDAOS = sellerRepository.findAll();

        List<UserDAO> allUsers = new ArrayList<>();
        allUsers.addAll(buyerDAOS);
        allUsers.addAll(sellerDAOS);

        return allUsers;
    }

    public List<BuyerDAO> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public List<SellerDAO> getAllSellers() {
        return sellerRepository.findAll();
    }

    public UserDAO getByEmail(String email) {
        Optional<BuyerDAO> buyerDAOOptional = buyerRepository.findByEmail(email);
        Optional<SellerDAO> sellerDAOOptional = sellerRepository.findByEmail(email);

        if (buyerDAOOptional.isPresent())
            return buyerDAOOptional.get();
        else if (sellerDAOOptional.isPresent())
            return sellerDAOOptional.get();
        else
            throw new ResourceNotFoundException("User not found with email: " + email);
    }

    public Boolean userExists(String email) {
        return buyerRepository.existsByEmail(email) || sellerRepository.existsByEmail(email);
    }

    public void saveNewBuyer(BuyerDAO buyerDAO) {
        if (userExists(buyerDAO.getEmail()))
            throw new ResourceAlreadyFoundException("User already found with email: " + buyerDAO.getEmail());
        buyerRepository.save(buyerDAO);
    }

    public void saveNewSeller(SellerDAO sellerDAO) {
        if (userExists(sellerDAO.getEmail()))
            throw new ResourceAlreadyFoundException("User already found with email: " + sellerDAO.getEmail());
        sellerRepository.save(sellerDAO);
    }

}
