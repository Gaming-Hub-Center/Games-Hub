package com.gameshub.config;

import com.gameshub.exception.PasswordMismatchException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class PasswordConfig extends DaoAuthenticationProvider {

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
                                                  throws AuthenticationException {

        try {
            super.additionalAuthenticationChecks(userDetails, authentication);
        } catch (BadCredentialsException e) {
            throw new PasswordMismatchException("Invalid username or password.");
        }
    }
}
