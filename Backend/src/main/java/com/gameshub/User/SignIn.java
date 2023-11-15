package com.gameshub.User;

import java.util.regex.*;

public class SignIn {

    private static final String EMAIL_REGEX = "^[\\w.]+@gameshub\\.com$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private final String email;
    private final String password;
    //TODO: UserEncoder for hashing, UserRepository for database

    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private boolean isValidFormat() {
        if (email == null)
            return false;
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private void checkEmailFormat() {
        boolean emailFormat = isValidFormat();
        if (!emailFormat)
            throw new RuntimeException("Email is written in a wrong format!");
    }

    public boolean authenticate() {
        checkEmailFormat();
        return !password.isEmpty();
    }

}
