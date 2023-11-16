package com.gameshub.Controller;

import com.gameshub.Exception.*;
import com.gameshub.Repository.*;
import com.gameshub.Utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:5173")
public class AuthController {

    @Autowired
    private BuyerRepository buyerRepository;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        try {
            return ResponseEntity.ok(signInRequest.authenticate(buyerRepository));
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

//    @GetMapping("buyers")
//    public List<Buyer> getAllBuyers() {
//        return buyerRepository.findAll();
//    }
//
//    @GetMapping("buyer")
//    public ResponseEntity<Buyer> getBuyer(@RequestParam String email) {
//        Buyer buyer = buyerRepository.findByEmail(email);
//        if (buyer != null)
//            return ResponseEntity.ok(buyer);
//        else
//            return ResponseEntity.notFound().build();
//    }

    // ===============================

}
