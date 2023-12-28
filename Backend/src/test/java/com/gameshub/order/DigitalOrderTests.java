package com.gameshub.order;

import com.gameshub.controller.DTO.order.*;
import com.gameshub.controller.order.*;
import com.gameshub.exception.*;
import com.gameshub.model.cart.*;
import com.gameshub.model.order.*;
import com.gameshub.model.product.*;
import com.gameshub.model.user.*;
import com.gameshub.repository.cart.*;
import com.gameshub.repository.order.*;
import com.gameshub.repository.product.*;
import com.gameshub.repository.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DigitalOrderTests {

    @Autowired
    OrderController orderController;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DigitalOrderItemRepository digitalOrderItemRepository;

    @Autowired
    DigitalCartRepository digitalCartRepository;

    @Autowired
    DigitalProductRepository digitalProductRepository;

    @BeforeEach
    public void setup() {
        digitalOrderItemRepository.deleteAll();
        orderRepository.deleteAll();
        digitalCartRepository.deleteAll();
        digitalProductRepository.deleteAll();
        sellerRepository.deleteAll();
        buyerRepository.deleteAll();

        orderRepository.resetAutoIncrement();
        digitalProductRepository.resetAutoIncrement();
        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();

        BuyerDAO buyerDAO1 = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 1000);
        BuyerDAO buyerDAO2 = new BuyerDAO("Jane Smith", "jane.smith@example.com", "$2a$10$GMRwzM/Li/rDwHDw4RNbBeQFRHEcwVSEBZ18D4NkUo5BiHd/oawP6", "+9876543210", "456 Oak Avenue", 4000);
        BuyerDAO buyerDAO3 = new BuyerDAO("Emily Johnson", "emily.johnson@example.com", "$2a$10$FbE4XSzchqlJEwCE08.t7O4f77XE81uezyeA9cw4sHVIZc86YNiU", "+1122334455", "789 Pine Road", 3500);

        SellerDAO sellerDAO1 = new SellerDAO("Alice Blue", "alice.blue@example.com", "$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.parse("2023-01-01"), "Description about Alice", "123456789A");
        SellerDAO sellerDAO2 = new SellerDAO("Bob Green", "+5647382910", "bob.green@example.com", "$2a$10$bM/Om9b6r/7qklzXJCCAauNotDkmKEsD2W.BA32PvOw5nIcOsRc3q", "202 Green Lane", 15000, "ID67890B", LocalDate.parse("2023-02-02"), "Description about Bob", "987654321B");

        DigitalProductDAO digitalProductDAO1 = new DigitalProductDAO("Digital Game Bundle", 300.00f, "Get the latest digital game bundle with multiple titles.", 1, 10, "Games", "DG001", LocalDate.parse("2023-12-08"));
        DigitalProductDAO digitalProductDAO2 = new DigitalProductDAO("Virtual Reality Gaming Experience", 200.00f, "Immersive VR gaming with a high-resolution headset.", 1, 15, "VR Games", "VR001", LocalDate.parse("2023-12-08"));
        DigitalProductDAO digitalProductDAO3 = new DigitalProductDAO("Wireless Gaming Controller Pack", 60.00f, "Bundle of ergonomic wireless controllers for various gaming platforms.", 2, 25, "Accessories", "WG001", LocalDate.parse("2023-12-08"));
        DigitalProductDAO digitalProductDAO4 = new DigitalProductDAO("High-Performance Gaming Laptop", 1000.00f, "Top-notch gaming laptop for an exceptional gaming experience.", 2, 5, "Computers", "HL001", LocalDate.parse("2023-12-08"));
        DigitalProductDAO digitalProductDAO5 = new DigitalProductDAO("Epic Quests Strategy Game", 50.00f, "Embark on epic quests with our latest strategy game.", 2, 2, "Games", "EQ001", LocalDate.parse("2023-12-08"));

        DigitalCartDAO digitalCartDAO1 = new DigitalCartDAO(1, 1, 5);
        DigitalCartDAO digitalCartDAO2 = new DigitalCartDAO(1, 3, 2);
        DigitalCartDAO digitalCartDAO3 = new DigitalCartDAO(1, 5, 2);
        DigitalCartDAO digitalCartDAO4 = new DigitalCartDAO(2, 2, 2);
        DigitalCartDAO digitalCartDAO5 = new DigitalCartDAO(2, 4, 7);
        DigitalCartDAO digitalCartDAO6 = new DigitalCartDAO(3, 2, 2);
        DigitalCartDAO digitalCartDAO7 = new DigitalCartDAO(3, 4, 1);

        buyerRepository.save(buyerDAO1);
        buyerRepository.save(buyerDAO2);
        buyerRepository.save(buyerDAO3);

        sellerRepository.save(sellerDAO1);
        sellerRepository.save(sellerDAO2);

        digitalProductRepository.save(digitalProductDAO1);
        digitalProductRepository.save(digitalProductDAO2);
        digitalProductRepository.save(digitalProductDAO3);
        digitalProductRepository.save(digitalProductDAO4);
        digitalProductRepository.save(digitalProductDAO5);

        digitalCartRepository.save(digitalCartDAO1);
        digitalCartRepository.save(digitalCartDAO2);
        digitalCartRepository.save(digitalCartDAO3);
        digitalCartRepository.save(digitalCartDAO4);
        digitalCartRepository.save(digitalCartDAO5);
        digitalCartRepository.save(digitalCartDAO6);
        digitalCartRepository.save(digitalCartDAO7);
    }

    @AfterEach
    public void finish() {
        digitalOrderItemRepository.deleteAll();
        orderRepository.deleteAll();
        digitalCartRepository.deleteAll();
        digitalProductRepository.deleteAll();
        sellerRepository.deleteAll();
        buyerRepository.deleteAll();

        orderRepository.resetAutoIncrement();
        digitalProductRepository.resetAutoIncrement();
        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();
    }

    @Test
    void testDigitalOrderSuccessful() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(1);
        orderCheckoutDTO.setPaymentMethod("cod");
        ResponseEntity<?> responseEntity = orderController.digitalCheckout(orderCheckoutDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void testDigitalOrderOutOfStock() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(2);
        orderCheckoutDTO.setPaymentMethod("cod");

        assertThrows(OutOfStockException.class, () -> {
            orderController.digitalCheckout(orderCheckoutDTO);
        });
    }

    @Test
    void testDigitalOrderInsufficientBalance() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(1);
        orderCheckoutDTO.setPaymentMethod("wallet");

        assertThrows(InsufficientBalanceException.class, () -> {
            orderController.digitalCheckout(orderCheckoutDTO);
        });
    }

    @Test
    void testDigitalOrderValidData() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(1);
        orderCheckoutDTO.setPaymentMethod("cod");
        ResponseEntity<?> responseEntity = orderController.digitalCheckout(orderCheckoutDTO);

        OrderDAO orderDAO = orderRepository.findById(1).orElse(null);

        assert orderDAO != null;

        int buyerId = orderDAO.getBuyerId();
        float price = orderDAO.getOrderPrice();
        String status = orderDAO.getOrderStatus();
        List<DigitalOrderItemDAO> digitalOrderItemDAOs = orderDAO.getDigitalOrderItemDAOs();

        assert buyerId == 1;
        assert price == 1720;
        assert Objects.equals(status, "Processing");
        assert digitalOrderItemDAOs.get(0).getId().getDigitalProductDAO().getId() == 1;
        assert digitalOrderItemDAOs.get(1).getId().getDigitalProductDAO().getId() == 3;
        assert digitalOrderItemDAOs.get(2).getId().getDigitalProductDAO().getId() == 5;
    }

    @Test
    void testDigitalOrderUpdateBuyerBalance() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(3);
        orderCheckoutDTO.setPaymentMethod("wallet");
        ResponseEntity<?> responseEntity = orderController.digitalCheckout(orderCheckoutDTO);

        float originalBalance = 3500;
        float orderPrice = 1400;

        BuyerDAO updatedBuyer = buyerRepository.findById(3).orElse(null);
        assertNotNull(updatedBuyer, "Updated buyer should not be null");

        float expectedBalance = originalBalance - orderPrice;
        assertEquals(expectedBalance, updatedBuyer.getBalance());
    }


    @Test
    void testDigitalOrderUpdateSellersBalances() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(3);
        orderCheckoutDTO.setPaymentMethod("wallet");
        ResponseEntity<?> responseEntity = orderController.digitalCheckout(orderCheckoutDTO);

        float originalBalance1 = 10000;
        float originalBalance2 = 15000;
        float orderPrice1 = 400;
        float orderPrice2 = 1000;

        SellerDAO updatedSeller1 = sellerRepository.findById(1).orElse(null);
        SellerDAO updatedSeller2 = sellerRepository.findById(2).orElse(null);
        assertNotNull(updatedSeller1, "Updated seller should not be null");
        assertNotNull(updatedSeller2, "Updated seller should not be null");

        float expectedBalance1 = originalBalance1 + orderPrice1;
        float expectedBalance2 = originalBalance2 + orderPrice2;
        assertEquals(expectedBalance1, updatedSeller1.getBalance());
        assertEquals(expectedBalance2, updatedSeller2.getBalance());
    }

    @Test
    void testDigitalOrderUpdateStockCount() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(3);
        orderCheckoutDTO.setPaymentMethod("wallet");
        ResponseEntity<?> responseEntity = orderController.digitalCheckout(orderCheckoutDTO);

        int originalStock1 = 15;
        int originalStock2 = 5;
        int count1 = 2;
        int count2 = 1;

        DigitalProductDAO updatedDigital1 = digitalProductRepository.findById(2).orElse(null);
        DigitalProductDAO updatedDigital2 = digitalProductRepository.findById(4).orElse(null);
        assertNotNull(updatedDigital1, "Updated product should not be null");
        assertNotNull(updatedDigital2, "Updated product should not be null");

        int expectedStock1 = originalStock1 - count1;
        int expectedStock2 = originalStock2 - count2;
        assertEquals(expectedStock1, updatedDigital1.getCount());
        assertEquals(expectedStock2, updatedDigital2.getCount());
    }

    @Test
    void testDigitalOrderClearBuyerCart() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(3);
        orderCheckoutDTO.setPaymentMethod("wallet");
        ResponseEntity<?> responseEntity = orderController.digitalCheckout(orderCheckoutDTO);

        List<DigitalCartDAO> updatedCart = digitalCartRepository.findByBuyerId(3);

        assertTrue(updatedCart.isEmpty());
    }

}
