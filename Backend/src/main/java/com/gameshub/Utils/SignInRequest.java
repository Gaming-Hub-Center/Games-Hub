package com.gameshub.Utils;

import com.gameshub.Exception.*;
import com.gameshub.Model.Users.*;
import com.gameshub.Repository.*;

import java.util.*;
import java.util.regex.*;

public class SignInRequest {

    private String email;
    private String password;

    private static final String EMAIL_REGEX = "^[\\w.]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    private boolean isValidFormat() {
        if (email == null)
            return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public void validateEmailFormat() {
        if (!EMAIL_PATTERN.matcher(email).matches())
            throw new InvalidFormatException("Email is written in a wrong format!");
    }

    public boolean authenticate(BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        validateEmailFormat();
        BuyerDAO buyer = buyerRepository.findByEmail(email).orElse(null);
        SellerDAO seller = sellerRepository.findByEmail(email).orElse(null);
        if (buyer == null && seller == null)
            throw new ResourceNotFoundException("User with email '" + email + "' not found.");
        if (buyer != null && !Objects.equals(this.password, buyer.getPassword()))
            throw new PasswordMismatchException("Password does not match.");
        if (seller != null && !Objects.equals(this.password, seller.getPassword()))
            throw new PasswordMismatchException("Password does not match.");
        return true;
    }

}