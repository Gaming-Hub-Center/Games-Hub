package com.gameshub.Controller;

import com.gameshub.Model.Users.*;
import com.gameshub.Service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:5173")
public class AuthController {

    @Autowired
    private BuyerDetailsService buyerDetailsService;

    @Autowired
    private SellerDetailsService sellerDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Logged in successfully");
    }

    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

//    @PostMapping("signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
//        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
//        try {
//            return ResponseEntity.ok(signInRequest.authenticate(buyerDetailsService, sellerService));
//        } catch (InvalidFormatException ex) {
//            return globalExceptionHandler.handleInvalidFormatException(ex);
//        } catch (ResourceNotFoundException ex) {
//            return globalExceptionHandler.handleResourceNotFoundException(ex);
//        } catch (PasswordMismatchException ex) {
//            return globalExceptionHandler.handlePasswordMismatchException(ex);
//        } catch (Exception ex) {
//            return globalExceptionHandler.handleGeneralException(ex);
//        }
//    }

    // =========== Testing ===========

    @GetMapping("buyers")
    public List<BuyerDAO> getAllBuyers() {
        return buyerDetailsService.getAll();
    }

    @GetMapping("buyer")
    public ResponseEntity<BuyerDAO> getBuyer(@RequestParam String email) {
        BuyerDAO buyerDAO = buyerDetailsService.getByEmail(email);
        if (buyerDAO != null)
            return ResponseEntity.ok(buyerDAO);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("sellers")
    public List<SellerDAO> getAllSellers() {
        return sellerDetailsService.getAll();
    }

    @GetMapping("seller")
    public ResponseEntity<SellerDAO> getSeller(@RequestParam String email) {
        SellerDAO sellerDAO = sellerDetailsService.getByEmail(email);
        if (sellerDAO != null)
            return ResponseEntity.ok(sellerDAO);
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("users")
    public List<Object> getAllUsers() {
        List<?> list1 = buyerDetailsService.getAll();
        List<?> list2 = sellerDetailsService.getAll();

        List<Object> combinedList = new ArrayList<>(list1);
        combinedList.addAll(list2);
        return combinedList;
    }

    @GetMapping("user")
    public ResponseEntity<Object> getUser(@RequestParam String email) {
        BuyerDAO buyerDAO = buyerDetailsService.getByEmail(email);
        SellerDAO sellerDAO = sellerDetailsService.getByEmail(email);
        if (buyerDAO != null)
            return ResponseEntity.ok(buyerDAO);
        return ResponseEntity.ok(sellerDAO);
    }

    // ===============================

}
