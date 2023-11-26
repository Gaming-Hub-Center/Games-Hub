package com.gameshub.Controller;

import com.gameshub.Exception.*;
import com.gameshub.Repository.*;
import com.gameshub.Utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.gameshub.Model.Users.*;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:5173")
public class AuthController {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        try {
            return ResponseEntity.ok(signInRequest.authenticate(buyerRepository, sellerRepository));
        } catch (InvalidFormatException ex) {
            return globalExceptionHandler.handleInvalidFormatException(ex);
        } catch (ResourceNotFoundException ex) {
            return globalExceptionHandler.handleResourceNotFoundException(ex);
        } catch (PasswordMismatchException ex) {
            return globalExceptionHandler.handlePasswordMismatchException(ex);
        } catch (Exception ex) {
            return globalExceptionHandler.handleGeneralException(ex);
        }
    }

    // =========== Testing ===========

    @GetMapping("buyers")
    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    @GetMapping("buyer")
    public ResponseEntity<Buyer> getBuyer(@RequestParam String email) {
        Buyer buyer = buyerRepository.findByEmail(email);
        if (buyer != null)
            return ResponseEntity.ok(buyer);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("sellers")
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @GetMapping("seller")
    public ResponseEntity<Seller> getSeller(@RequestParam String email) {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller != null)
            return ResponseEntity.ok(seller);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("users")
    public List<Object> getAllUsers() {
        List<?> list1 = buyerRepository.findAll();
        List<?> list2 = sellerRepository.findAll();

        List<Object> combinedList = new ArrayList<>(list1);
        combinedList.addAll(list2);
        return combinedList;
    }

    @GetMapping("user")
    public ResponseEntity<Object> getUser(@RequestParam String email) {
        Buyer buyer = buyerRepository.findByEmail(email);
        Seller seller = sellerRepository.findByEmail(email);
        if (buyer != null)
            return ResponseEntity.ok(buyer);
        return ResponseEntity.ok(seller);
    }

    // ===============================

}