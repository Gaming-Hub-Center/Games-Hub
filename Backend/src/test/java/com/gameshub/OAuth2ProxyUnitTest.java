package com.gameshub;

import com.gameshub.Exception.ResourceNotFoundException;
import com.gameshub.Exception.UserAlreadyExistsException;
import com.gameshub.Model.Users.DAOs.BuyerDAO;
import com.gameshub.Model.Users.DAOs.SellerDAO;
import com.gameshub.Repository.BuyerRepository;
import com.gameshub.Repository.SellerRepository;
import com.gameshub.google_oauth2.service.BuyerServiceOAuth2;
import com.gameshub.google_oauth2.service.SellerServiceOAuth2;
import com.gameshub.google_oauth2.service.proxy.CreateUserProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OAuth2ProxyUnitTest {

    @InjectMocks
    private CreateUserProxy createUserProxy;

    @Mock
    private SellerServiceOAuth2 sellerService;

    @Mock
    private SellerRepository sellerRepository;

    @Mock
    private BuyerServiceOAuth2 buyerServiceOAuth2;

    @Mock
    private BuyerRepository buyerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessUserCreation_NullOAuth2User() {
        assertThrows(IllegalArgumentException.class, () -> {
            createUserProxy.processBuyerCreation(null);
        });
    }

    @Test
    void testProcessUserCreation_MissingUserID() {
        OidcIdToken idToken = mock(OidcIdToken.class);
        when(idToken.getClaim("sub")).thenReturn(null);
        assertThrows(ResourceNotFoundException.class, () -> {
            createUserProxy.processBuyerCreation(idToken);
        });
    }

    @Test
    void testProcessUserCreation_UserExists() {
        BuyerDAO buyer = new BuyerDAO(1111, "Rowaina", "Email.com", null, null, null, 0);
        when(buyerRepository.saveAndFlush(any(BuyerDAO.class))).thenReturn(buyer);
        BuyerDAO savedBuyer = buyerRepository.saveAndFlush(buyer);
        verify(buyerRepository, times(1)).saveAndFlush(buyer);
        assertEquals(buyer, savedBuyer); // Optionally assert the saved buyer if needed
    }

    @Test
    void testProcessSellerCreation_MissingIdToken() {
        assertThrows(IllegalArgumentException.class, () -> {
            createUserProxy.processSellerCreation(null);
        });
    }

    @Test
    void testProcessBuyerCreation_MissingIdToken() {
        assertThrows(IllegalArgumentException.class, () -> {
            createUserProxy.processBuyerCreation(null);
        });
    }

    @Test
    void testProcessSellerCreation_UserAlreadyExists() {
        OidcIdToken idToken = mock(OidcIdToken.class);
        when(idToken.getClaim("sub")).thenReturn("123");
        when(idToken.getClaim("email")).thenReturn("seller@example.com");
        when(sellerRepository.findByEmail("seller@example.com")).thenReturn(Optional.of(new SellerDAO()));

        assertThrows(UserAlreadyExistsException.class, () -> {
            createUserProxy.processSellerCreation(idToken);
        });
    }

    @Test
    void testProcessBuyerCreation_UserAlreadyExists() {
        OidcIdToken idToken = mock(OidcIdToken.class);
        when(idToken.getClaim("sub")).thenReturn("123");
        when(idToken.getClaim("email")).thenReturn("buyer@example.com");
        when(buyerRepository.findByEmail("buyer@example.com")).thenReturn(Optional.of(new BuyerDAO()));

        assertThrows(UserAlreadyExistsException.class, () -> {
            createUserProxy.processBuyerCreation(idToken);
        });
    }

    @Test
    void testProcessSellerCreation_Success() {
        OidcIdToken idToken = mock(OidcIdToken.class);
        when(idToken.getClaim("sub")).thenReturn("123");
        when(idToken.getClaim("email")).thenReturn("seller@example.com");
        when(sellerRepository.findByEmail("seller@example.com")).thenReturn(Optional.empty());

        createUserProxy.processSellerCreation(idToken);

        verify(sellerService).createUser(idToken);
    }

    @Test
    void testProcessBuyerCreation_Success() {
        OidcIdToken idToken = mock(OidcIdToken.class);
        when(idToken.getClaim("sub")).thenReturn("123");
        when(idToken.getClaim("email")).thenReturn("seller@example.com");
        when(buyerRepository.findByEmail("seller@example.com")).thenReturn(Optional.empty());

        createUserProxy.processBuyerCreation(idToken);

        verify(buyerServiceOAuth2).createUser(idToken);
    }

}
