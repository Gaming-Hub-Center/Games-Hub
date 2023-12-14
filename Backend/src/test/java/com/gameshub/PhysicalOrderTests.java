package com.gameshub;

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

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PhysicalOrderTests {

    @Autowired
    OrderController orderController;

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PhysicalOrderItemRepository physicalOrderItemRepository;

    @Autowired
    PhysicalCartRepository physicalCartRepository;

    @Autowired
    PhysicalProductRepository physicalProductRepository;

    @BeforeEach
    public void setup() {
        physicalOrderItemRepository.deleteAll();
        orderRepository.deleteAll();
        physicalCartRepository.deleteAll();
        physicalProductRepository.deleteAll();
        sellerRepository.deleteAll();
        buyerRepository.deleteAll();

        orderRepository.resetAutoIncrement();
        physicalProductRepository.resetAutoIncrement();
        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();

        BuyerDAO buyerDAO1 = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 1000);
        BuyerDAO buyerDAO2 = new BuyerDAO("Jane Smith", "+9876543210", "jane.smith@example.com", "456 Oak Avenue", "$2a$10$GMRwzM/Li/rDwHDw4RNbBeQFRHEcwVSEBZ18D4NkUo5BiHd/oawP6", 4000);

        SellerDAO sellerDAO1 = new SellerDAO("Alice Blue", "alice.blue@example.com", "$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.parse("2023-01-01"), "Description about Alice", "123456789A");
        SellerDAO sellerDAO2 = new SellerDAO("Bob Green", "+5647382910", "bob.green@example.com", "$2a$10$bM/Om9b6r/7qklzXJCCAauNotDkmKEsD2W.BA32PvOw5nIcOsRc3q", "202 Green Lane", 15000, "ID67890B", LocalDate.parse("2023-02-02"), "Description about Bob", "987654321B");

        PhysicalProductDAO physicalProductDAO1 = new PhysicalProductDAO("Game Console", 300.0f, "Latest model of our popular game console.", 1, 10, "Electronics", LocalDate.parse("2023-12-08"));
        PhysicalProductDAO physicalProductDAO2 = new PhysicalProductDAO("Virtual Reality Headset", 200.0f, "Immersive VR experience with high resolution.", 1, 15, "Electronics", LocalDate.parse("2023-12-08"));
        PhysicalProductDAO physicalProductDAO3 = new PhysicalProductDAO("Wireless Gaming Controller", 60.0f, "Ergonomic wireless controller for various gaming platforms.", 2, 25, "Accessories", LocalDate.parse("2023-12-08"));
        PhysicalProductDAO physicalProductDAO4 = new PhysicalProductDAO("Gaming Laptop", 1000.0f, "High-performance laptop designed for gaming.", 2, 5, "Computers", LocalDate.parse("2023-12-08"));
        PhysicalProductDAO physicalProductDAO5 = new PhysicalProductDAO("Strategy Game", 50.0f, "A new strategy game with epic quests.", 2, 2, "Games", LocalDate.parse("2023-12-08"));

        PhysicalCartDAO physicalCartDAO1 = new PhysicalCartDAO(1, 1, 5);
        PhysicalCartDAO physicalCartDAO2 = new PhysicalCartDAO(1, 3, 2);
        PhysicalCartDAO physicalCartDAO3 = new PhysicalCartDAO(1, 5, 2);
        PhysicalCartDAO physicalCartDAO4 = new PhysicalCartDAO(2, 2, 2);
        PhysicalCartDAO physicalCartDAO5 = new PhysicalCartDAO(2, 4, 7);

        buyerRepository.save(buyerDAO1);
        buyerRepository.save(buyerDAO2);

        sellerRepository.save(sellerDAO1);
        sellerRepository.save(sellerDAO2);

        physicalProductRepository.save(physicalProductDAO1);
        physicalProductRepository.save(physicalProductDAO2);
        physicalProductRepository.save(physicalProductDAO3);
        physicalProductRepository.save(physicalProductDAO4);
        physicalProductRepository.save(physicalProductDAO5);

        physicalCartRepository.save(physicalCartDAO1);
        physicalCartRepository.save(physicalCartDAO2);
        physicalCartRepository.save(physicalCartDAO3);
        physicalCartRepository.save(physicalCartDAO4);
        physicalCartRepository.save(physicalCartDAO5);
    }

    @AfterEach
    public void finish() {
        physicalOrderItemRepository.deleteAll();
        orderRepository.deleteAll();
        physicalCartRepository.deleteAll();
        physicalProductRepository.deleteAll();
        sellerRepository.deleteAll();
        buyerRepository.deleteAll();

        orderRepository.resetAutoIncrement();
        physicalProductRepository.resetAutoIncrement();
        sellerRepository.resetAutoIncrement();
        buyerRepository.resetAutoIncrement();
    }

    @Test
    void testPhysicalOrderSuccessful() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(1);
        orderCheckoutDTO.setPaymentMethod("cod");
        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);

        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }

    @Test
    void testPhysicalOrderOutOfStock() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(2);
        orderCheckoutDTO.setPaymentMethod("cod");

        assertThrows(OutOfStockException.class, () -> {
            orderController.physicalCheckout(orderCheckoutDTO);
        });
    }

    @Test
    void testPhysicalOrderInsufficientBalance() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(1);
        orderCheckoutDTO.setPaymentMethod("wallet");

        assertThrows(InsufficientBalanceException.class, () -> {
            orderController.physicalCheckout(orderCheckoutDTO);
        });
    }

    @Test
    void testPhysicalOrderValidData() {
        OrderCheckoutDTO orderCheckoutDTO = new OrderCheckoutDTO();
        orderCheckoutDTO.setBuyerID(1);
        orderCheckoutDTO.setPaymentMethod("cod");
        ResponseEntity<?> responseEntity = orderController.physicalCheckout(orderCheckoutDTO);

        OrderDAO orderDAO = orderRepository.findById(1).orElse(null);

        assert orderDAO != null;

        BuyerDAO buyerDAO = orderDAO.getBuyerDAO();
        float price = orderDAO.getOrderPrice();
        String status = orderDAO.getOrderStatus();
        List<PhysicalOrderItemDAO> physicalOrderItemDAOs = orderDAO.getPhysicalOrderItemDAOs();

        assert buyerDAO.getId() == 1;
        assert price == 1720;
        assert Objects.equals(status, "Processing");
        assert physicalOrderItemDAOs.get(0).getId().getPhysicalProductDAO().getId() == 1;
        assert physicalOrderItemDAOs.get(1).getId().getPhysicalProductDAO().getId() == 3;
        assert physicalOrderItemDAOs.get(2).getId().getPhysicalProductDAO().getId() == 5;
    }

    @Test
    void testPhysicalOrderUpdateBuyerBalance() {

    }

    @Test
    void testPhysicalOrderUpdateSellersBalances() {

    }

    @Test
    void testPhysicalOrderUpdateStockCount() {

    }

    @Test
    void testPhysicalOrderClearBuyerCart() {

    }

}
