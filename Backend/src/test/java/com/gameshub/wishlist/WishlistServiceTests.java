//package com.gameshub.wishlist;
//
//import com.gameshub.model.product.DigitalProductDAO;
//import com.gameshub.model.product.PhysicalProductDAO;
//import com.gameshub.model.user.BuyerDAO;
//import com.gameshub.model.user.SellerDAO;
//import com.gameshub.model.wishlist.DigitalWishlistDAO;
//import com.gameshub.model.wishlist.PhysicalWishlistDAO;
//import com.gameshub.repository.product.DigitalProductRepository;
//import com.gameshub.repository.product.PhysicalProductRepository;
//import com.gameshub.repository.user.BuyerRepository;
//import com.gameshub.repository.user.SellerRepository;
//import com.gameshub.repository.wishlist.DigitalWishlistRepository;
//import com.gameshub.repository.wishlist.PhysicalWishlistRepository;
//import com.gameshub.service.wishlist.WishlistService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.dao.DuplicateKeyException;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class WishlistServiceTests {
//
//    @Autowired
//    private WishlistService wishlistService;
//
//    // Repositories
//    @Autowired
//    private DigitalWishlistRepository digitalWishlistRepository;
//    @Autowired
//    private PhysicalWishlistRepository physicalWishlistRepository;
//    @Autowired
//    private BuyerRepository buyerRepository;
//    @Autowired
//    private SellerRepository sellerRepository;
//    @Autowired
//    private PhysicalProductRepository physicalProductRepository;
//    @Autowired
//    private DigitalProductRepository digitalProductRepository;
//
//    // DAOs
//    private PhysicalWishlistDAO testPhysicalWishlist;
//    private DigitalWishlistDAO testDigitalWishlist;
//    private BuyerDAO testBuyer;
//    private SellerDAO testSeller;
//    private PhysicalProductDAO testProductPhysical;
//    private DigitalProductDAO testProductDigital;
//
//    @BeforeEach
//    void setUp() {
//        digitalProductRepository.deleteAll();
//        digitalProductRepository.resetAutoIncrement();
//        physicalProductRepository.deleteAll();
//        physicalProductRepository.resetAutoIncrement();
//        sellerRepository.deleteAll();
//        sellerRepository.resetAutoIncrement();
//        buyerRepository.deleteAll();
//        buyerRepository.resetAutoIncrement();
//        physicalWishlistRepository.deleteAll();
//        digitalWishlistRepository.deleteAll();
//
//        // Create a test seller
//        testSeller = new SellerDAO("Test Seller", "seller@example.com", "password", "1234567890", "Seller Address", 100.0f, "123456789", LocalDate.now(), "Test Description", "VAT123456");
//        sellerRepository.save(testSeller);
//
//        // Create a test buyer
//        testBuyer = new BuyerDAO("buyer", "email@gmail.com", "password", "1234567890", "Buyer Address", 50f);
//        buyerRepository.save(testBuyer);
//
//        LocalDate localDate = LocalDate.now();
//
//        // Create a test product and associate it with the test seller
//        testProductPhysical = new PhysicalProductDAO("Test ProductP", 50.0f, "Description", testSeller.getId(), 10, "Category", localDate);
//        physicalProductRepository.save(testProductPhysical);
//
//        testProductDigital = new DigitalProductDAO("Test ProductD", 50.0f, "Description", testSeller.getId(), 10, "Category", "123", localDate);
//        digitalProductRepository.save(testProductDigital);
//    }
//
//    @Test
//    @Transactional
//    public void test_addNewPhysicalWishlistProduct() {
//        wishlistService.addPhysicalWishlistProduct(testBuyer.getId(), testProductPhysical.getId());
//
//        // Fetch the wishlist item
//        PhysicalWishlistDAO.WishlistKey WishlistKey = new PhysicalWishlistDAO.WishlistKey(testBuyer.getId(), testProductPhysical.getId());
//        PhysicalWishlistDAO savedWishlistItem = physicalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Cart item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductPhysical.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<PhysicalWishlistDAO> buyerWishlistItems = wishlistService.getPhysicalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//    }
//
//    @Test
//    @Transactional
//    public void test_addNewDigitalWishlistProduct() {
//        wishlistService.addDigitalWishlistProduct(testBuyer.getId(), testProductDigital.getId());
//
//        // Fetch the wishlist item
//        DigitalWishlistDAO.WishlistKey WishlistKey = new DigitalWishlistDAO.WishlistKey(testBuyer.getId(), testProductDigital.getId());
//        DigitalWishlistDAO savedWishlistItem = digitalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Cart item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductDigital.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<DigitalWishlistDAO> buyerWishlistItems = wishlistService.getDigitalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//    }
//
//    @Test
//    @Transactional
//    public void test_addDupPhysicalWishlistProduct() {
//        wishlistService.addPhysicalWishlistProduct(testBuyer.getId(), testProductPhysical.getId());
//
//        // Fetch the wishlist item
//        PhysicalWishlistDAO.WishlistKey WishlistKey = new PhysicalWishlistDAO.WishlistKey(testBuyer.getId(), testProductPhysical.getId());
//        PhysicalWishlistDAO savedWishlistItem = physicalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Cart item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductPhysical.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        // Try to add the same item again
//        assertThrows(DuplicateKeyException.class, () -> {
//            wishlistService.addPhysicalWishlistProduct(testBuyer.getId(), testProductPhysical.getId());
//        });
//
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<PhysicalWishlistDAO> buyerWishlistItems = wishlistService.getPhysicalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//    }
//
//    @Test
//    @Transactional
//    public void test_DupNewDigitalWishlistProduct() {
//        wishlistService.addDigitalWishlistProduct(testBuyer.getId(), testProductDigital.getId());
//
//        // Fetch the wishlist item
//        DigitalWishlistDAO.WishlistKey WishlistKey = new DigitalWishlistDAO.WishlistKey(testBuyer.getId(), testProductDigital.getId());
//        DigitalWishlistDAO savedWishlistItem = digitalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Cart item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductDigital.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        // Try to add the same item again
//        assertThrows(DuplicateKeyException.class, () -> {
//            wishlistService.addDigitalWishlistProduct(testBuyer.getId(), testProductDigital.getId());
//        });
//
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<DigitalWishlistDAO> buyerWishlistItems = wishlistService.getDigitalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//    }
//
//    @Test
//    @Transactional
//    public void test_deleteDigitalWishlistProduct() {
//        wishlistService.addDigitalWishlistProduct(testBuyer.getId(), testProductDigital.getId());
//
//        // Fetch the wishlist item
//        DigitalWishlistDAO.WishlistKey WishlistKey = new DigitalWishlistDAO.WishlistKey(testBuyer.getId(), testProductDigital.getId());
//        DigitalWishlistDAO savedWishlistItem = digitalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Cart item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductDigital.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        System.out.println(savedWishlistItem.getId().getBuyerID());
//        System.out.println(savedWishlistItem.getId().getProductID());
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<DigitalWishlistDAO> buyerWishlistItems = wishlistService.getDigitalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//
//        // Delete the wishlist item
//        wishlistService.deleteDigitalWishlistProduct(testBuyer.getId(), testProductDigital.getId());
//        // Fetch all wishlist items for the buyer and assert the count is 0
//        List<DigitalWishlistDAO> buyerWishlistItems_after = wishlistService.getDigitalWishlistItems(testBuyer.getId());
//        assertEquals(0, buyerWishlistItems_after.size(), "The number of cart items should be " + "0");
//    }
//
//    @Test
//    @Transactional
//    public void test_deletePhysicalWishlistProduct() {
//        wishlistService.addPhysicalWishlistProduct(testBuyer.getId(), testProductPhysical.getId());
//
//        // Fetch the wishlist item
//        PhysicalWishlistDAO.WishlistKey WishlistKey = new PhysicalWishlistDAO.WishlistKey(testBuyer.getId(), testProductPhysical.getId());
//        PhysicalWishlistDAO savedWishlistItem = physicalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Cart item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductPhysical.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        System.out.println(savedWishlistItem.getId().getBuyerID());
//        System.out.println(savedWishlistItem.getId().getProductID());
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<PhysicalWishlistDAO> buyerWishlistItems = wishlistService.getPhysicalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//
//        // Delete the wishlist item
//        wishlistService.deletePhysicalWishlistProduct(testBuyer.getId(), testProductPhysical.getId());
//        // Fetch all wishlist items for the buyer and assert the count is 0
//        List<PhysicalWishlistDAO> buyerWishlistItems_after = wishlistService.getPhysicalWishlistItems(testBuyer.getId());
//        assertEquals(0, buyerWishlistItems_after.size(), "The number of cart items should be " + "1");
//    }
//
//    @Test
//    @Transactional
//    public void test_MultipleNewPhysicalWishlistProducts() {
//        wishlistService.addPhysicalWishlistProduct(testBuyer.getId(), testProductPhysical.getId());
//        System.out.println(testProductPhysical.getId());
//        // Fetch the wishlist item
//        PhysicalWishlistDAO.WishlistKey WishlistKey = new PhysicalWishlistDAO.WishlistKey(testBuyer.getId(), testProductPhysical.getId());
//        PhysicalWishlistDAO savedWishlistItem = physicalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Wishlist item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductPhysical.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<PhysicalWishlistDAO> buyerWishlistItems = wishlistService.getPhysicalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//
//        // Add another item to the wishlist
//
//        LocalDate localDate = LocalDate.now();
//        PhysicalProductDAO product = new PhysicalProductDAO("Test ProductP2", 50.0f, "Description", testSeller.getId(), 10, "Category", localDate);
//        physicalProductRepository.save(product);
//        wishlistService.addPhysicalWishlistProduct(testBuyer.getId(), product.getId());
//
//        // Fetch all wishlist items for the buyer and assert the count is 2
//        List<PhysicalWishlistDAO> buyerWishlistItems2 = wishlistService.getPhysicalWishlistItems(testBuyer.getId());
//        assertEquals(2, buyerWishlistItems2.size(), "The number of cart items should be " + "2");
//
//        // Delete the wishlist items
//        wishlistService.deletePhysicalWishlistProduct(testBuyer.getId(), testProductPhysical.getId());
//        wishlistService.deletePhysicalWishlistProduct(testBuyer.getId(), product.getId());
//        // Fetch all wishlist items for the buyer and assert the count is 0
//        List<PhysicalWishlistDAO> buyerWishlistItems_after = wishlistService.getPhysicalWishlistItems(testBuyer.getId());
//        assertEquals(0, buyerWishlistItems_after.size(), "The number of cart items should be " + "1");
//    }
//
//    @Test
//    @Transactional
//    public void test_MultipleNewDigitalWishlistProducts() {
//        wishlistService.addDigitalWishlistProduct(testBuyer.getId(), testProductDigital.getId());
//
//        // Fetch the wishlist item
//        DigitalWishlistDAO.WishlistKey WishlistKey = new DigitalWishlistDAO.WishlistKey(testBuyer.getId(), testProductDigital.getId());
//        DigitalWishlistDAO savedWishlistItem = digitalWishlistRepository.findById(WishlistKey).orElse(null);
//
//        assertNotNull(savedWishlistItem, "Wishlist item should not be null");
//        assertEquals(testBuyer.getId(), savedWishlistItem.getId().getBuyerID(), "Buyer ID should match");
//        assertEquals(testProductDigital.getId(), savedWishlistItem.getId().getProductID(), "Product ID should match");
//
//        // Fetch all wishlist items for the buyer and assert the count is 1
//        List<DigitalWishlistDAO> buyerWishlistItems = wishlistService.getDigitalWishlistItems(testBuyer.getId());
//        assertEquals(1, buyerWishlistItems.size(), "The number of cart items should be " + "1");
//
//        // Add another item to the wishlist
//        LocalDate localDate = LocalDate.now();
//        DigitalProductDAO product = new DigitalProductDAO("Test ProductD2", 50.0f, "Description", testSeller.getId(), 10, "Category", "123", localDate);
//        digitalProductRepository.save(product);
//        wishlistService.addDigitalWishlistProduct(testBuyer.getId(), product.getId());
//
//        // Fetch all wishlist items for the buyer and assert the count is 2
//        List<DigitalWishlistDAO> buyerWishlistItems2 = wishlistService.getDigitalWishlistItems(testBuyer.getId());
//        assertEquals(2, buyerWishlistItems2.size(), "The number of cart items should be " + "2");
//
//        // Delete the wishlist items
//        wishlistService.deleteDigitalWishlistProduct(testBuyer.getId(), testProductDigital.getId());
//        wishlistService.deleteDigitalWishlistProduct(testBuyer.getId(), product.getId());
//        // Fetch all wishlist items for the buyer and assert the count is 0
//        List<DigitalWishlistDAO> buyerWishlistItems_after = wishlistService.getDigitalWishlistItems(testBuyer.getId());
//        assertEquals(0, buyerWishlistItems_after.size(), "The number of cart items should be " + "0");
//    }
//}
