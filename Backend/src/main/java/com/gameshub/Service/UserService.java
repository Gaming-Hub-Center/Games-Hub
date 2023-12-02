package com.gameshub.Service;

import com.gameshub.Exception.*;
import com.gameshub.Model.User.DAO.*;
import com.gameshub.Model.User.DTO.*;
import com.gameshub.Repository.*;
import com.gameshub.Utils.*;
import lombok.*;
import org.springframework.security.crypto.password.*;
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
