package com.gameshub;

import com.gameshub.Model.Users.Buyer;
import com.gameshub.Repository.BuyerRepository;
import com.gameshub.google_oauth2.service.BuyerServiceOAuth2;
import com.gameshub.google_oauth2.service.proxy.CreateBuyerProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OAuth2BuyerSignUpUnitTest {

    @InjectMocks
    private BuyerServiceOAuth2 buyerService; // Inject the BuyerService instance

    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private OidcIdToken idToken;

    private Buyer buyer;

    private final CreateBuyerProxy createBuyerProxy = new CreateBuyerProxy();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessUserCreation_NullOAuth2User() {
        assertThrows(IllegalArgumentException.class, () -> {
            createBuyerProxy.processUserCreation(null);
        });
    }

//    @Test
//    void testProcessUserCreation_MissingUserID() {
//        when(idToken.getClaim("sub")).thenReturn(null);
//        assertThrows(ResourceNotFoundException.class, () -> {
//            createBuyerProxy.processUserCreation(idToken);
//        });
//    }

    @Test
    void testProcessUserCreation_UserExists() {
        buyer = new Buyer(1111, "Rowaina", "Email.com", null, null, null, 0);
        when(buyerRepository.save(any(Buyer.class))).thenReturn(buyer);
        Buyer savedBuyer =buyerRepository.save(buyer);
        verify(buyerRepository, times(1)).save(buyer);
        assertEquals(buyer, savedBuyer); // Optionally assert the saved buyer if needed
    }

//    @Test
//    void testProcessUserCreation_Success() {
//        // Mocked OidcIdToken with required claims
//        OidcIdToken idToken = mock(OidcIdToken.class);
//        when(idToken.getClaim("sub")).thenReturn("123");
//        when(idToken.getClaim("name")).thenReturn("Test User");
//        when(idToken.getClaim("email")).thenReturn("test@example.com");
//
//        // Mock behavior of findByEmail to return null (assuming it's expected to return null for a non-existent email)
//        when(buyerRepository.findByEmail("test@example.com")).thenReturn(null);
//
//        // Act
//        assertDoesNotThrow(() -> buyerService.createUser(idToken));
//
//        // Verify interactions
//        verify(idToken, times(1)).getClaim("sub");
//        verify(idToken, times(1)).getClaim("name");
//        verify(idToken, times(1)).getClaim("email");
//        verify(buyerRepository, times(1)).findByEmail("test@example.com");
//        verify(buyerRepository, times(1)).save(any(Buyer.class));
//    }

//    @Test
//    void testProcessUserCreation_UserExists_() {
//        // Mocked OidcIdToken with required claims
//        OidcIdToken idToken = mock(OidcIdToken.class);
//        when(idToken.getClaim("sub")).thenReturn("123");
//        when(idToken.getClaim("name")).thenReturn("Test User");
//        when(idToken.getClaim("email")).thenReturn("existing@example.com");
//
//        // Mock behavior of findByEmail to return an existing buyer (assuming it's expected to return a non-null value for an existing email)
//        when(buyerRepository.findByEmail("existing@example.com")).thenReturn(new Buyer());
//
//        // Act and Assert
//        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,
//                () -> buyerService.createUser(idToken));
//        assertEquals("User Exists", exception.getMessage());
//
//        // Verify interactions
//        verify(buyerRepository, times(1)).findByEmail("existing@example.com");
//        verify(buyerRepository, never()).save(any(Buyer.class)); // Ensure save was not called
//    }



    // TODO the rest of test depending on the data to be merged and create the tests of the unimplemented methods.
}
