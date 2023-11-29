package com.gameshub;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.gameshub.Model.Users.DAOs.BuyerDAO;
import com.gameshub.Model.Users.DAOs.SellerDAO;
import com.gameshub.Repository.BuyerRepository;
import com.gameshub.Repository.SellerRepository;
import com.gameshub.google_oauth2.service.BuyerServiceOAuth2;
import com.gameshub.google_oauth2.service.SellerServiceOAuth2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

import java.util.Optional;

@SpringBootTest
class OAuthServiceUnitTest {

    @InjectMocks
    private BuyerServiceOAuth2 buyerService;

    @Mock
    private BuyerRepository buyerRepository;

    @InjectMocks
    private SellerServiceOAuth2 sellerService;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private OidcIdToken oidcIdToken;

    private static final String EMAIL = "test@example.com";
    private static final String NAME = "Test User";
    private static final String SUB = "123";

    @BeforeEach
    void setUp() {
        when(oidcIdToken.getClaim("email")).thenReturn(EMAIL);
        when(oidcIdToken.getClaim("name")).thenReturn(NAME);
        when(oidcIdToken.getClaim("sub")).thenReturn(SUB);
    }

    @Test
    void emailAlreadyExist_EmailExists() {
        BuyerDAO existingBuyer = new BuyerDAO();
        when(buyerRepository.findByEmail(EMAIL)).thenReturn(Optional.of(existingBuyer));

        boolean exists = buyerService.emailAlreadyExist(oidcIdToken);

        assertTrue(exists);
    }

    @Test
    void emailAlreadyExist_EmailDoesNotExist() {
        // Arrange
        when(buyerRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        // Act
        boolean exists = buyerService.emailAlreadyExist(oidcIdToken);

        // Assert
        assertFalse(exists);
    }


    @Test
    void emailAlreadyExist_EmailExistsSeller() {
        SellerDAO existingSeller = new SellerDAO();
        when(sellerRepository.findByEmail(EMAIL)).thenReturn(Optional.of(existingSeller));

        boolean exists = sellerService.emailAlreadyExist(oidcIdToken);

        assertTrue(exists);
    }

    @Test
    void emailAlreadyExist_EmailDoesNotExistSeller() {
        // Arrange
        when(sellerRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        // Act
        boolean exists = sellerService.emailAlreadyExist(oidcIdToken);

        // Assert
        assertFalse(exists);
    }
}
