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
        BuyerDAO buyerDAO2 = new BuyerDAO("Jane Smith", "+9876543210", "jane.smith@example.com", "456 Oak Avenue", "$2a$10$GMRwzM/Li/rDwHDw4RNbBeQFRHEcwVSEBZ18D4NkUo5BiHd/oawP6", 4000);

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

        buyerRepository.save(buyerDAO1);
        buyerRepository.save(buyerDAO2);

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

        BuyerDAO buyerDAO = orderDAO.getBuyerDAO();
        float price = orderDAO.getOrderPrice();
        String status = orderDAO.getOrderStatus();
        List<DigitalOrderItemDAO> digitalOrderItemDAOs = orderDAO.getDigitalOrderItemDAOs();

        assert buyerDAO.getId() == 1;
        assert price == 1720;
        assert Objects.equals(status, "Processing");
        assert digitalOrderItemDAOs.get(0).getId().getDigitalProductDAO().getId() == 1;
        assert digitalOrderItemDAOs.get(1).getId().getDigitalProductDAO().getId() == 3;
        assert digitalOrderItemDAOs.get(2).getId().getDigitalProductDAO().getId() == 5;
    }

    @Test
    void testDigitalOrderUpdateBuyerBalance() {

    }

    @Test
    void testDigitalOrderUpdateSellersBalances() {

    }

    @Test
    void testDigitalOrderUpdateStockCount() {

    }

    @Test
    void testDigitalOrderClearBuyerCart() {

    }

}
