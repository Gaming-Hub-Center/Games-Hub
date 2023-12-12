package com.gameshub.DataAccessLayer;



import com.gameshub.model.cart.DigitalCartDAO;
import com.gameshub.model.cart.PhysicalCartDAO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.*;
import com.gameshub.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PhysicalProductRepository physicalProductRepository;
    @Autowired
    private DigitalProductRepository digitalProductRepository;
    @Autowired
    private PhysicalCartRepository physicalCartRepository;
    @Autowired
    private DigitalCartRepository digitalCartRepository;

    private BuyerDAO testBuyer;
    private SellerDAO testSeller;
    private PhysicalProductDAO testProductPhysical;
    private DigitalProductDAO testProductDigital;

    @BeforeEach
    void setUp() {

        // Create a test seller
        testSeller = new SellerDAO("Test Seller", "seller@example.com", "password", "1234567890", "Seller Address", 100.0f, "123456789", LocalDate.now(), "Test Description", "VAT123456");
        sellerRepository.save(testSeller);

        // Create a test buyer
        testBuyer = new BuyerDAO(1,"buyer","buyer","email@gmail.com","pw","0100",50);
        buyerRepository.save(testBuyer);
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // Create a test product and associate it with the test seller
        testProductPhysical = new PhysicalProductDAO(0, 50.0f, 10, "Test Product", "Description", "Category", testSeller.getId(), date);
        testProductDigital = new DigitalProductDAO(0, 50.0f, 10, "Test Product", "Description", "Category", testSeller.getId(), date,"123");
        physicalProductRepository.save(testProductPhysical);
        digitalProductRepository.save(testProductDigital);
    }

    @Test
    void add_update_delete_Physical_Cart_item() {
        int initialCount = 5;
        cartService.addOrUpdatePhysicalCartItem(testBuyer.getId(), testProductPhysical.getProductID(), initialCount);
        System.out.println("buyer id"+testBuyer.getId());

        // Fetch the cart item
        PhysicalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(testBuyer.getId(), testProductPhysical.getProductID());
        PhysicalCartDAO savedCartItem = physicalCartRepository.findById(cartKey).orElse(null);

        assertNotNull(savedCartItem, "Cart item should not be null");
        assertEquals(initialCount, savedCartItem.getCount(), "Cart item count should match the initial count");

        // Test updating the same cart item
        int updatedCount = 10;
        cartService.addOrUpdatePhysicalCartItem(testBuyer.getId(), testProductPhysical.getProductID(), updatedCount);
        savedCartItem = physicalCartRepository.findById(cartKey).orElse(null);

        assertNotNull(savedCartItem, "Cart item should still not be null after update");
        assertEquals(updatedCount, savedCartItem.getCount(), "Cart item count should match the updated count");

        cartService.removePhysicalCartItem(testBuyer.getId(), testProductPhysical.getProductID());
        PhysicalCartDAO removedCartItem = physicalCartRepository.findById(cartKey).orElse(null);

        assertNull(removedCartItem, "Cart item should be null after removal");
    }


    @Test
    void addMultipleAndDeletePhysicalCartItems() {
        int countPerItem = 1; // Assuming you want to add one of each product to the cart
        List<PhysicalProductDAO> products = new ArrayList<>();
        List<PhysicalCartDAO.CartKey> cartKeys = new ArrayList<>();

        // Add 50 products and cart items
        for (int i = 0; i < 50; i++) {
            // Create a new product for each cart item
            PhysicalProductDAO product = new PhysicalProductDAO(0, 50.0f, 10, "Test Product " + i, "Description " + i, "Category", testSeller.getId(), new Date());
            physicalProductRepository.save(product);
            products.add(product);

            // Add to cart
            cartService.addOrUpdatePhysicalCartItem(testBuyer.getId(), product.getProductID(), countPerItem);

            // Store cart key for later checks
            PhysicalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(testBuyer.getId(), product.getProductID());
            cartKeys.add(cartKey);

            // Verify the cart item is added
            PhysicalCartDAO savedCartItem = physicalCartRepository.findById(cartKey).orElse(null);
            assertNotNull(savedCartItem, "Cart item " + i + " should not be null");
            assertEquals(countPerItem, savedCartItem.getCount(), "Cart item " + i + " count should match the initial count");
        }

        // Fetch all cart items for the buyer and assert the count is 50
        List<PhysicalCartDAO> buyerCartItems = cartService.getPhysicalCartItems(testBuyer.getId());
        assertEquals(50, buyerCartItems.size(), "The number of cart items should be " + "50");

        // Delete each cart item and verify removal
        for (int i = 0; i < cartKeys.size(); i++) {
            // Remove cart item
            cartService.removePhysicalCartItem(testBuyer.getId(), products.get(i).getProductID());

            // Verify the cart item is removed
            PhysicalCartDAO removedCartItem = physicalCartRepository.findById(cartKeys.get(i)).orElse(null);
            assertNull(removedCartItem, "Cart item " + i + " should be null after removal");
        }
        // Fetch all cart items for the buyer and assert the count is 50
        buyerCartItems = cartService.getPhysicalCartItems(testBuyer.getId());
        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
    }

    @Test
    void addMultipleAndDeleteDigitalCartItems() {
        int countPerItem = 1; // Assuming you want to add one of each product to the cart
        List<DigitalProductDAO> products = new ArrayList<>();
        List<DigitalCartDAO.CartKey> cartKeys = new ArrayList<>();

        // Add 50 products and cart items
        for (int i = 0; i < 50; i++) {
            // Create a new product for each cart item
            DigitalProductDAO product = new DigitalProductDAO(0, 50.0f, 10, "Test Product " + i, "Description " + i, "Category", testSeller.getId(), new Date(),"123");
            digitalProductRepository.save(product);
            products.add(product);

            // Add to cart
            cartService.addOrUpdateDigitalCartItem(testBuyer.getId(), product.getProductID(), countPerItem);

            // Store cart key for later checks
            DigitalCartDAO.CartKey cartKey = new DigitalCartDAO.CartKey(testBuyer.getId(), product.getProductID());
            cartKeys.add(cartKey);

            // Verify the cart item is added
            DigitalCartDAO savedCartItem = digitalCartRepository.findById(cartKey).orElse(null);
            assertNotNull(savedCartItem, "Cart item " + i + " should not be null");
            assertEquals(countPerItem, savedCartItem.getCount(), "Cart item " + i + " count should match the initial count");
        }

        // Fetch all cart items for the buyer and assert the count is 50
        List<DigitalCartDAO> buyerCartItems = cartService.getDigitalCartItems(testBuyer.getId());
        assertEquals(50, buyerCartItems.size(), "The number of cart items should be " + "50");

        // Delete each cart item and verify removal
        for (int i = 0; i < cartKeys.size(); i++) {
            // Remove cart item
            cartService.removeDigitalCartItem(testBuyer.getId(), products.get(i).getProductID());

            // Verify the cart item is removed
            DigitalCartDAO removedCartItem = digitalCartRepository.findById(cartKeys.get(i)).orElse(null);
            assertNull(removedCartItem, "Cart item " + i + " should be null after removal");
        }
        // Fetch all cart items for the buyer and assert the count is 50
        buyerCartItems = cartService.getDigitalCartItems(testBuyer.getId());
        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
    }


    @Test
    void add_update_delete_Digital_Cart_item() {
        int initialCount = 5;
        cartService.addOrUpdateDigitalCartItem(testBuyer.getId(), testProductDigital.getProductID(), initialCount);

        // Fetch the cart item
        DigitalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(testBuyer.getId(), testProductDigital.getProductID());
        DigitalCartDAO savedCartItem = digitalCartRepository.findById(cartKey).orElse(null);

        assertNotNull(savedCartItem, "Cart item should not be null");
        assertEquals(initialCount, savedCartItem.getCount(), "Cart item count should match the initial count");

        // Test updating the same cart item
        int updatedCount = 10;
        cartService.addOrUpdateDigitalCartItem(testBuyer.getId(), testProductDigital.getProductID(), updatedCount);
        savedCartItem = digitalCartRepository.findById(cartKey).orElse(null);

        assertNotNull(savedCartItem, "Cart item should still not be null after update");
        assertEquals(updatedCount, savedCartItem.getCount(), "Cart item count should match the updated count");

        cartService.removeDigitalCartItem(testBuyer.getId(), testProductDigital.getProductID());
        DigitalCartDAO removedCartItem = digitalCartRepository.findById(cartKey).orElse(null);

        assertNull(removedCartItem, "Cart item should be null after removal");
    }

    @Test
    void getNonExistentPhysicalCartItems() {
        List<PhysicalCartDAO> buyerCartItems = cartService.getPhysicalCartItems(-999);
        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
    }

    @Test
    void getNonExistentDigitalCartItems() {
        List<DigitalCartDAO> buyerCartItems = cartService.getDigitalCartItems(-999);
        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
    }


}
