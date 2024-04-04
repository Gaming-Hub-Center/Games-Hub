//package com.gameshub.order;
//
//import com.gameshub.dto.order.OrderCheckoutDTO;
//import com.gameshub.controller.order.*;
//import com.gameshub.exception.*;
//import com.gameshub.model.cart.*;
//import com.gameshub.model.order.*;
//import com.gameshub.model.product.*;
//import com.gameshub.model.user.*;
//import com.gameshub.repository.cart.*;
//import com.gameshub.repository.order.*;
//import com.gameshub.repository.product.*;
//import com.gameshub.repository.user.*;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.context.*;
//import org.springframework.http.*;
//
//import java.time.*;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class PhysicalOrderTests {
//
//    @Autowired
//    OrderController orderController;
//
//    @Autowired
//    BuyerRepository buyerRepository;
//
//    @Autowired
//    SellerRepository sellerRepository;
//
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    PhysicalOrderItemRepository physicalOrderItemRepository;
//
//    @Autowired
//    PhysicalCartRepository physicalCartRepository;
//
//    @Autowired
//    PhysicalProductRepository physicalProductRepository;
//
//    @BeforeEach
//    public void setup() {
//        physicalOrderItemRepository.deleteAll();
//        orderRepository.deleteAll();
//        physicalCartRepository.deleteAll();
//        physicalProductRepository.deleteAll();
//        sellerRepository.deleteAll();
//        buyerRepository.deleteAll();
//
//        orderRepository.resetAutoIncrement();
//        physicalProductRepository.resetAutoIncrement();
//        sellerRepository.resetAutoIncrement();
//        buyerRepository.resetAutoIncrement();
//
//        BuyerDAO buyerDAO1 = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 1000);
//        BuyerDAO buyerDAO2 = new BuyerDAO("Jane Smith", "jane.smith@example.com", "$2a$10$GMRwzM/Li/rDwHDw4RNbBeQFRHEcwVSEBZ18D4NkUo5BiHd/oawP6", "+9876543210", "456 Oak Avenue", 4000);
//        BuyerDAO buyerDAO3 = new BuyerDAO("Emily Johnson", "emily.johnson@example.com", "$2a$10$FbE4XSzchqlJEwCE08.t7O4f77XE81uezyeA9cw4sHVIZc86YNiU", "+1122334455", "789 Pine Road", 3500);
//
//        SellerDAO sellerDAO1 = new SellerDAO("Alice Blue", "alice.blue@example.com", "$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.parse("2023-01-01"), "Description about Alice", "123456789A");
//        SellerDAO sellerDAO2 = new SellerDAO("Bob Green", "+5647382910", "bob.green@example.com", "$2a$10$bM/Om9b6r/7qklzXJCCAauNotDkmKEsD2W.BA32PvOw5nIcOsRc3q", "202 Green Lane", 15000, "ID67890B", LocalDate.parse("2023-02-02"), "Description about Bob", "987654321B");
//
//        PhysicalProductDAO physicalProductDAO1 = new PhysicalProductDAO("Game Console", 300.0f, "Latest model of our popular game console.", 1, 10, "Electronics", LocalDate.parse("2023-12-08"));
//        PhysicalProductDAO physicalProductDAO2 = new PhysicalProductDAO("Virtual Reality Headset", 200.0f, "Immersive VR experience with high resolution.", 1, 15, "Electronics", LocalDate.parse("2023-12-08"));
//        PhysicalProductDAO physicalProductDAO3 = new PhysicalProductDAO("Wireless Gaming Controller", 60.0f, "Ergonomic wireless controller for various gaming platforms.", 2, 25, "Accessories", LocalDate.parse("2023-12-08"));
//        PhysicalProductDAO physicalProductDAO4 = new PhysicalProductDAO("Gaming Laptop", 1000.0f, "High-performance laptop designed for gaming.", 2, 5, "Computers", LocalDate.parse("2023-12-08"));
//        PhysicalProductDAO physicalProductDAO5 = new PhysicalProductDAO("Strategy Game", 50.0f, "A new strategy game with epic quests.", 2, 2, "Games", LocalDate.parse("2023-12-08"));
//
//        PhysicalCartDAO physicalCartDAO1 = new PhysicalCartDAO(1, 1, 5);
//        PhysicalCartDAO physicalCartDAO2 = new PhysicalCartDAO(1, 3, 2);
//        PhysicalCartDAO physicalCartDAO3 = new PhysicalCartDAO(1, 5, 2);
//        PhysicalCartDAO physicalCartDAO4 = new PhysicalCartDAO(2, 2, 2);
//        PhysicalCartDAO physicalCartDAO5 = new PhysicalCartDAO(2, 4, 7);
//        PhysicalCartDAO physicalCartDAO6 = new PhysicalCartDAO(3, 2, 2);
//        PhysicalCartDAO physicalCartDAO7 = new PhysicalCartDAO(3, 4, 1);
//
//        buyerRepository.save(buyerDAO1);
//        buyerRepository.save(buyerDAO2);
//        buyerRepository.save(buyerDAO3);
//
//        sellerRepository.save(sellerDAO1);
//        sellerRepository.save(sellerDAO2);
//
//        physicalProductRepository.save(physicalProductDAO1);
//        physicalProductRepository.save(physicalProductDAO2);
//        physicalProductRepository.save(physicalProductDAO3);
//        physicalProductRepository.save(physicalProductDAO4);
//        physicalProductRepository.save(physicalProductDAO5);
//
//        physicalCartRepository.save(physicalCartDAO1);
//        physicalCartRepository.save(physicalCartDAO2);
//        physicalCartRepository.save(physicalCartDAO3);
//        physicalCartRepository.save(physicalCartDAO4);
//        physicalCartRepository.save(physicalCartDAO5);
//        physicalCartRepository.save(physicalCartDAO6);
//        physicalCartRepository.save(physicalCartDAO7);
//    }
//
//    @AfterEach
//    public void finish() {
//        physicalOrderItemRepository.deleteAll();
//        orderRepository.deleteAll();
//        physicalCartRepository.deleteAll();
//        physicalProductRepository.deleteAll();
//        sellerRepository.deleteAll();
//        buyerRepository.deleteAll();
//
//        orderRepository.resetAutoIncrement();
//        physicalProductRepository.resetAutoIncrement();
//        sellerRepository.resetAutoIncrement();
//        buyerRepository.resetAutoIncrement();
//    }
//
//    @Test
//    void testPhysicalOrderSuccessful() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(1);
//        orderCheckoutDTO.setPaymentMethod("cod");
//        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);
//
//        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
//    }
//
//    @Test
//    void testPhysicalOrderOutOfStock() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(2);
//        orderCheckoutDTO.setPaymentMethod("cod");
//
//        assertThrows(OutOfStockException.class, () -> {
//            orderController.physicalCheckout(orderCheckoutDTO);
//        });
//    }
//
//    @Test
//    void testPhysicalOrderInsufficientBalance() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(1);
//        orderCheckoutDTO.setPaymentMethod("wallet");
//
//        assertThrows(InsufficientBalanceException.class, () -> {
//            orderController.physicalCheckout(orderCheckoutDTO);
//        });
//    }
//
//    @Test
//    void testPhysicalOrderValidData() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(1);
//        orderCheckoutDTO.setPaymentMethod("cod");
//        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);
//
//        OrderDAO orderDAO = orderRepository.findById(1).orElse(null);
//
//        assert orderDAO != null;
//
//        int buyerId = orderDAO.getBuyerId();
//        float price = orderDAO.getOrderPrice();
//        String status = orderDAO.getOrderStatus();
//        List<PhysicalOrderItemDAO> physicalOrderItemDAOs = orderDAO.getPhysicalOrderItemDAOs();
//
//        assert buyerId == 1;
//        assert price == 1720;
//        assert Objects.equals(status, "Processing");
//        assert physicalOrderItemDAOs.get(0).getId().getPhysicalProductDAO().getId() == 1;
//        assert physicalOrderItemDAOs.get(1).getId().getPhysicalProductDAO().getId() == 3;
//        assert physicalOrderItemDAOs.get(2).getId().getPhysicalProductDAO().getId() == 5;
//    }
//
//    @Test
//    void testPhysicalOrderUpdateBuyerBalance() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(3);
//        orderCheckoutDTO.setPaymentMethod("wallet");
//        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);
//
//        float originalBalance = 3500;
//        float orderPrice = 1400;
//
//        BuyerDAO updatedBuyer = buyerRepository.findById(3).orElse(null);
//        assertNotNull(updatedBuyer, "Updated buyer should not be null");
//
//        float expectedBalance = originalBalance - orderPrice;
//        assertEquals(expectedBalance, updatedBuyer.getBalance());
//    }
//
//    @Test
//    void testPhysicalOrderUpdateSellersBalances() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(3);
//        orderCheckoutDTO.setPaymentMethod("cod");
//        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);
//
//        float originalBalance1 = 10000;
//        float originalBalance2 = 15000;
//        float orderPrice1 = 400;
//        float orderPrice2 = 1000;
//
//        SellerDAO updatedSeller1 = sellerRepository.findById(1).orElse(null);
//        SellerDAO updatedSeller2 = sellerRepository.findById(2).orElse(null);
//        assertNotNull(updatedSeller1, "Updated seller should not be null");
//        assertNotNull(updatedSeller2, "Updated seller should not be null");
//
//        float expectedBalance1 = originalBalance1 + orderPrice1;
//        float expectedBalance2 = originalBalance2 + orderPrice2;
//        assertEquals(expectedBalance1, updatedSeller1.getBalance());
//        assertEquals(expectedBalance2, updatedSeller2.getBalance());
//    }
//
//    @Test
//    void testPhysicalOrderUpdateStockCount() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(3);
//        orderCheckoutDTO.setPaymentMethod("wallet");
//        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);
//
//        int originalStock1 = 15;
//        int originalStock2 = 5;
//        int count1 = 2;
//        int count2 = 1;
//
//        PhysicalProductDAO updatedDigital1 = physicalProductRepository.findById(2).orElse(null);
//        PhysicalProductDAO updatedDigital2 = physicalProductRepository.findById(4).orElse(null);
//        assertNotNull(updatedDigital1, "Updated product should not be null");
//        assertNotNull(updatedDigital2, "Updated product should not be null");
//
//        int expectedStock1 = originalStock1 - count1;
//        int expectedStock2 = originalStock2 - count2;
//        assertEquals(expectedStock1, updatedDigital1.getCount());
//        assertEquals(expectedStock2, updatedDigital2.getCount());
//    }
//
//    @Test
//    void testPhysicalOrderClearBuyerCart() {
//        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
//        orderCheckoutDTO.setBuyerID(3);
//        orderCheckoutDTO.setPaymentMethod("wallet");
//        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);
//
//        List<PhysicalCartDAO> updatedCart = physicalCartRepository.findByBuyerId(3);
//
//        assertTrue(updatedCart.isEmpty());
//    }
//
//}
