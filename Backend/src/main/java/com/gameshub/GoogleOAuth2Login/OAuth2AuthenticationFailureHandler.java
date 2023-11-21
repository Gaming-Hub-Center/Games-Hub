package com.gameshub.GoogleOAuth2Login;

import jakarta.servlet.ServletException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        // Custom logic upon authentication failure
        // For example, redirecting to a failure page:
        System.out.println("Failure"); // TODO Take appropriate action

        super.onAuthenticationFailure(request, response, exception);
    }
}
