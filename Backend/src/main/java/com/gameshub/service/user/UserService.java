package com.gameshub.service.user;

import com.gameshub.exception.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

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

    public BuyerDAO getBuyerById(int buyerID) {
        Optional<BuyerDAO> buyerDAOOptional = buyerRepository.findById(buyerID);

        if (buyerDAOOptional.isPresent())
            return buyerDAOOptional.get();
        else
            throw new ResourceNotFoundException("User not found with id: " + buyerID);
    }

    public SellerDAO getSellerById(int sellerID) {
        Optional<SellerDAO> sellerDAOOptional = sellerRepository.findById(sellerID);

        if (sellerDAOOptional.isPresent())
            return sellerDAOOptional.get();
        else
            throw new ResourceNotFoundException("User not found with id: " + sellerID);
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
