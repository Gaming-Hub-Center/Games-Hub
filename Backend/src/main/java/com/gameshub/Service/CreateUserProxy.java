package com.gameshub.Service;


import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.Repository.BuyerRepository;
import com.gameshub.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.stereotype.Component;

@Component
public class CreateUserProxy {

    @Autowired
    private SellerServiceOAuth2 sellerServiceOAuth2;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private BuyerServiceOAuth2 buyerServiceOAuth2;

    @Autowired
    private BuyerRepository buyerRepository;

    public void processSellerCreation(OidcIdToken idToken) {
        validateIdToken(idToken);

        String email = getEmailFromIdToken(idToken);

        validateSellerNotExistence(sellerRepository, email);

        sellerServiceOAuth2.createUser(idToken);
    }

    public void processBuyerCreation(OidcIdToken idToken) {
        validateIdToken(idToken);

        String email = getEmailFromIdToken(idToken);

        validateBuyerNotExistence(buyerRepository, email);

        buyerServiceOAuth2.createUser(idToken);
    }

    private void validateIdToken(OidcIdToken idToken) {
        if (idToken == null) {
            throw new IllegalArgumentException("OAuth2User cannot be null");
        }
        if (idToken.getClaim("sub") == null) {
            throw new ResourceNotFoundException("User ID is missing");
        }
    }

    private String getEmailFromIdToken(OidcIdToken idToken) {
        Object emailClaim = idToken.getClaim("email");
        if (emailClaim == null) {
            throw new ResourceNotFoundException("Email is missing");
        }
        return emailClaim.toString();
    }

    private void validateSellerNotExistence(SellerRepository repository, String email) {
        repository.findByEmail(email)
                .ifPresent(s -> {
                    throw new UserAlreadyExistsException("User Exists");
                });
    }

    private void validateBuyerNotExistence(BuyerRepository repository, String email) {
        repository.findByEmail(email)
                .ifPresent(s -> {
                    throw new UserAlreadyExistsException("User Exists");
                });
    }

}