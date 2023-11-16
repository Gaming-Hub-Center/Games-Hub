package com.gameshub.Utils;

import com.gameshub.Exception.*;
import com.gameshub.Model.*;
import com.gameshub.Repository.*;

import java.util.*;
import java.util.regex.*;

public class SignInRequest {

    private String email;
    private String password;

    private static final String EMAIL_REGEX = "^[\\w.]+@gameshub\\.com$";
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
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidFormatException("Email is written in a wrong format!");
        }
    }

    public boolean authenticate(BuyerRepository buyerRepository) {
        validateEmailFormat();
        Buyer buyer = buyerRepository.findByEmail(email);
        if (buyer == null)
            throw new ResourceNotFoundException("User with email '" + email + "' not found.");
        if (!Objects.equals(this.password, buyer.getPassword()))
            throw new PasswordMismatchException("Password does not match.");
        return true;
    }

}
