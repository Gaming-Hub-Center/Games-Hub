package com.gameshub.UpdateProfile;

import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import com.gameshub.service.user.UserService;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UpdateProfileTest {
    @Mock
    private BuyerRepository buyerRepository;

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateBuyer_Success() {
        // Arrange
        BuyerDAO buyer = new BuyerDAO();
        buyer.setId(1);
        buyer.setEmail("buyer@example.com");
        when(buyerRepository.findById(1)).thenReturn(Optional.of(buyer));

        // Act
        userService.updateBuyer(1, "New Name", "newbuyer@example.com", "1234567890", "New Address", 100.0f);

        // Assert
        verify(buyerRepository).save(buyerArgumentCaptor.capture());
        BuyerDAO capturedBuyer = buyerArgumentCaptor.getValue();
        assertEquals("New Name", capturedBuyer.getName());
        assertEquals("newbuyer@example.com", capturedBuyer.getEmail());
        assertEquals("1234567890", capturedBuyer.getPhone());
        assertEquals("New Address", capturedBuyer.getAddress());
        assertEquals(100.0f, capturedBuyer.getBalance());
    }

    @Test
    void updateBuyer_NotFound() {
        // Arrange
        when(buyerRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.updateBuyer(1, "New Name", "newbuyer@example.com", "1234567890", "New Address", 100.0f));
    }

    @Test
    void updateSeller_Success() {
        // Arrange
        SellerDAO seller = new SellerDAO();
        seller.setId(1);
        seller.setEmail("seller@example.com");
        when(sellerRepository.findById(1)).thenReturn(Optional.of(seller));

        // Act
        userService.updateSeller(1, "New Name", "newseller@example.com", "1234567890", "New Address", "New Description");

        // Assert
        verify(sellerRepository).save(sellerArgumentCaptor.capture());
        SellerDAO capturedSeller = sellerArgumentCaptor.getValue();
        assertEquals("New Name", capturedSeller.getName());
        assertEquals("newseller@example.com", capturedSeller.getEmail());
        assertEquals("1234567890", capturedSeller.getPhone());
        assertEquals("New Address", capturedSeller.getAddress());
        assertEquals("New Description", capturedSeller.getSellerDescription());
    }

    @Test
    void updateSeller_NotFound() {
        // Arrange
        when(sellerRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.updateSeller(1, "New Name", "newseller@example.com", "1234567890", "New Address", "New Description"));
    }

    // Capture arguments passed to save method
    @Captor
    private ArgumentCaptor<BuyerDAO> buyerArgumentCaptor;

    @Captor
    private ArgumentCaptor<SellerDAO> sellerArgumentCaptor;
}
