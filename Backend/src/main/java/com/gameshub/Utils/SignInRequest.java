package com.gameshub.Utils;

import com.gameshub.Exception.*;
import com.gameshub.Model.Users.*;
import com.gameshub.Service.*;

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

    public boolean authenticate(BuyerDetailsService buyerDetailsService, SellerDetailsService sellerDetailsService) {
        validateEmailFormat();
        BuyerDAO buyerDAO = buyerDetailsService.getByEmail(email);
        SellerDAO sellerDAO = sellerDetailsService.getByEmail(email);
        if (buyerDAO == null && sellerDAO == null)
            throw new ResourceNotFoundException("User with email '" + email + "' not found.");
        if (buyerDAO != null && !Objects.equals(this.password, buyerDAO.getPassword()))
            throw new PasswordMismatchException("Password does not match.");
        if (sellerDAO != null && !Objects.equals(this.password, sellerDAO.getPassword()))
            throw new PasswordMismatchException("Password does not match.");
        return true;
    }

}
