//package com.gameshub.cart;
//
//import com.gameshub.model.cart.*;
//import com.gameshub.model.product.*;
//import com.gameshub.model.user.*;
//import com.gameshub.repository.cart.*;
//import com.gameshub.repository.product.*;
//import com.gameshub.repository.user.*;
//import com.gameshub.service.cart.*;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.context.*;
//import org.springframework.transaction.annotation.*;
//
//import java.time.*;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class CartServiceTest {
//
//    @Autowired
//    private CartService cartService;
//
//    @Autowired
//    private BuyerRepository buyerRepository;
//
//    @Autowired
//    private SellerRepository sellerRepository;
//
//    @Autowired
//    private PhysicalProductRepository physicalProductRepository;
//
//    @Autowired
//    private DigitalProductRepository digitalProductRepository;
//
//    @Autowired
//    private PhysicalCartRepository physicalCartRepository;
//
//    @Autowired
//    private DigitalCartRepository digitalCartRepository;
//
//    private BuyerDAO testBuyer;
//    private SellerDAO testSeller;
//    private PhysicalProductDAO testProductPhysical;
//    private DigitalProductDAO testProductDigital;
//
//    @BeforeEach
//    void setUp() {
//
//        // Create a test seller
//        testSeller = new SellerDAO("Test Seller", "seller@example.com", "password", "1234567890", "Seller Address", 100.0f, "123456789", LocalDate.now(), "Test Description", "VAT123456");
//        sellerRepository.save(testSeller);
//
//        // Create a test buyer
//        testBuyer = new BuyerDAO("buyer","buyer","email@gmail.com","pw","0100",50);
//        buyerRepository.save(testBuyer);
//        LocalDate localDate = LocalDate.now();
//        // Create a test product and associate it with the test seller
//        testProductPhysical = new PhysicalProductDAO("Test Product", 50.0f, "Description", testSeller.getId(), 10, "Category", localDate);
//        testProductDigital = new DigitalProductDAO("Test Product", 50.0f, "Description", testSeller.getId(), 10, "Category", "123", localDate);
//        physicalProductRepository.save(testProductPhysical);
//        digitalProductRepository.save(testProductDigital);
//    }
//
//    @Test
//    void add_update_delete_Physical_Cart_item() {
//        int initialCount = 5;
//        cartService.addOrUpdatePhysicalCartItem(testBuyer.getId(), testProductPhysical.getId(), initialCount);
//        System.out.println("buyer id"+testBuyer.getId());
//
//        // Fetch the cart item
//        PhysicalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(testBuyer.getId(), testProductPhysical.getId());
//        PhysicalCartDAO savedCartItem = physicalCartRepository.findById(cartKey).orElse(null);
//
//        assertNotNull(savedCartItem, "Cart item should not be null");
//        assertEquals(initialCount, savedCartItem.getCount(), "Cart item count should match the initial count");
//
//        // Test updating the same cart item
//        int updatedCount = 10;
//        cartService.addOrUpdatePhysicalCartItem(testBuyer.getId(), testProductPhysical.getId(), updatedCount);
//        savedCartItem = physicalCartRepository.findById(cartKey).orElse(null);
//
//        assertNotNull(savedCartItem, "Cart item should still not be null after update");
//        assertEquals(updatedCount, savedCartItem.getCount(), "Cart item count should match the updated count");
//
//        cartService.removePhysicalCartItem(testBuyer.getId(), testProductPhysical.getId());
//        PhysicalCartDAO removedCartItem = physicalCartRepository.findById(cartKey).orElse(null);
//
//        assertNull(removedCartItem, "Cart item should be null after removal");
//    }
//
//
//    @Test
//    void addMultipleAndDeletePhysicalCartItems() {
//        int countPerItem = 1; // Assuming you want to add one of each product to the cart
//        List<PhysicalProductDAO> products = new ArrayList<>();
//        List<PhysicalCartDAO.CartKey> cartKeys = new ArrayList<>();
//
//        // Add 50 products and cart items
//        for (int i = 0; i < 50; i++) {
//            // Create a new product for each cart item
//            PhysicalProductDAO product = new PhysicalProductDAO("Test Product " + i, 50.0f, "Description " + i, testSeller.getId(), 10, "Category", LocalDate.now());
//            physicalProductRepository.save(product);
//            products.add(product);
//
//            // Add to cart
//            cartService.addOrUpdatePhysicalCartItem(testBuyer.getId(), product.getId(), countPerItem);
//
//            // Store cart key for later checks
//            PhysicalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(testBuyer.getId(), product.getId());
//            cartKeys.add(cartKey);
//
//            // Verify the cart item is added
//            PhysicalCartDAO savedCartItem = physicalCartRepository.findById(cartKey).orElse(null);
//            assertNotNull(savedCartItem, "Cart item " + i + " should not be null");
//            assertEquals(countPerItem, savedCartItem.getCount(), "Cart item " + i + " count should match the initial count");
//        }
//
//        // Fetch all cart items for the buyer and assert the count is 50
//        List<PhysicalCartDAO> buyerCartItems = cartService.getPhysicalCartItems(testBuyer.getId());
//        assertEquals(50, buyerCartItems.size(), "The number of cart items should be " + "50");
//
//        // Delete each cart item and verify removal
//        for (int i = 0; i < cartKeys.size(); i++) {
//            // Remove cart item
//            cartService.removePhysicalCartItem(testBuyer.getId(), products.get(i).getId());
//
//            // Verify the cart item is removed
//            PhysicalCartDAO removedCartItem = physicalCartRepository.findById(cartKeys.get(i)).orElse(null);
//            assertNull(removedCartItem, "Cart item " + i + " should be null after removal");
//        }
//        // Fetch all cart items for the buyer and assert the count is 50
//        buyerCartItems = cartService.getPhysicalCartItems(testBuyer.getId());
//        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
//    }
//
//    @Test
//    void addMultipleAndDeleteDigitalCartItems() {
//        int countPerItem = 1; // Assuming you want to add one of each product to the cart
//        List<DigitalProductDAO> products = new ArrayList<>();
//        List<DigitalCartDAO.CartKey> cartKeys = new ArrayList<>();
//
//        // Add 50 products and cart items
//        for (int i = 0; i < 50; i++) {
//            // Create a new product for each cart item
//            DigitalProductDAO product = new DigitalProductDAO("Test Product " + i, 50.0f, "Description " + i, testSeller.getId(), 10, "Category", "123", LocalDate.now());
//            digitalProductRepository.save(product);
//            products.add(product);
//
//            // Add to cart
//            cartService.addOrUpdateDigitalCartItem(testBuyer.getId(), product.getId(), countPerItem);
//
//            // Store cart key for later checks
//            DigitalCartDAO.CartKey cartKey = new DigitalCartDAO.CartKey(testBuyer.getId(), product.getId());
//            cartKeys.add(cartKey);
//
//            // Verify the cart item is added
//            DigitalCartDAO savedCartItem = digitalCartRepository.findById(cartKey).orElse(null);
//            assertNotNull(savedCartItem, "Cart item " + i + " should not be null");
//            assertEquals(countPerItem, savedCartItem.getCount(), "Cart item " + i + " count should match the initial count");
//        }
//
//        // Fetch all cart items for the buyer and assert the count is 50
//        List<DigitalCartDAO> buyerCartItems = cartService.getDigitalCartItems(testBuyer.getId());
//        assertEquals(50, buyerCartItems.size(), "The number of cart items should be " + "50");
//
//        // Delete each cart item and verify removal
//        for (int i = 0; i < cartKeys.size(); i++) {
//            // Remove cart item
//            cartService.removeDigitalCartItem(testBuyer.getId(), products.get(i).getId());
//
//            // Verify the cart item is removed
//            DigitalCartDAO removedCartItem = digitalCartRepository.findById(cartKeys.get(i)).orElse(null);
//            assertNull(removedCartItem, "Cart item " + i + " should be null after removal");
//        }
//        // Fetch all cart items for the buyer and assert the count is 50
//        buyerCartItems = cartService.getDigitalCartItems(testBuyer.getId());
//        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
//    }
//
//
//    @Test
//    void add_update_delete_Digital_Cart_item() {
//        int initialCount = 5;
//        cartService.addOrUpdateDigitalCartItem(testBuyer.getId(), testProductDigital.getId(), initialCount);
//
//        // Fetch the cart item
//        DigitalCartDAO.CartKey cartKey = new PhysicalCartDAO.CartKey(testBuyer.getId(), testProductDigital.getId());
//        DigitalCartDAO savedCartItem = digitalCartRepository.findById(cartKey).orElse(null);
//
//        assertNotNull(savedCartItem, "Cart item should not be null");
//        assertEquals(initialCount, savedCartItem.getCount(), "Cart item count should match the initial count");
//
//        // Test updating the same cart item
//        int updatedCount = 10;
//        cartService.addOrUpdateDigitalCartItem(testBuyer.getId(), testProductDigital.getId(), updatedCount);
//        savedCartItem = digitalCartRepository.findById(cartKey).orElse(null);
//
//        assertNotNull(savedCartItem, "Cart item should still not be null after update");
//        assertEquals(updatedCount, savedCartItem.getCount(), "Cart item count should match the updated count");
//
//        cartService.removeDigitalCartItem(testBuyer.getId(), testProductDigital.getId());
//        DigitalCartDAO removedCartItem = digitalCartRepository.findById(cartKey).orElse(null);
//
//        assertNull(removedCartItem, "Cart item should be null after removal");
//    }
//
//    @Test
//    void getNonExistentPhysicalCartItems() {
//        List<PhysicalCartDAO> buyerCartItems = cartService.getPhysicalCartItems(-999);
//        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
//    }
//
//    @Test
//    void getNonExistentDigitalCartItems() {
//        List<DigitalCartDAO> buyerCartItems = cartService.getDigitalCartItems(-999);
//        assertEquals(0, buyerCartItems.size(), "The number of cart items should be " + "0");
//    }
//
//}