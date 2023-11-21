package com.gameshub.GoogleOAuth2Login;

import jakarta.servlet.ServletException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        // Custom logic upon successful authentication
        // For example, redirecting to a different page:
        System.out.println("Success");  // TODO Take appropriate action

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
