package com.gameshub.Security;

import com.gameshub.Exception.*;
import com.gameshub.Service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

@Component
@Lazy
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BuyerDetailsService buyerDetailsService;

    @Autowired
    private SellerDetailsService sellerDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails;
        try {
            userDetails = buyerDetailsService.loadUserByUsername(email);
        } catch (ResourceNotFoundException ex) {
            userDetails = sellerDetailsService.loadUserByUsername(email);
        }

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
        } else {
            throw new PasswordMismatchException("Invalid credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
