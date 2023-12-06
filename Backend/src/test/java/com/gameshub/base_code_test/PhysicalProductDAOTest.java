//package com.gameshub.base_code_test;
//
//import com.gameshub.model.product.PhysicalProductDAO;
//import org.junit.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PhysicalProductDAOTest {
//    @Test
//    void testBuilder() {
//        LocalDate postDate = LocalDate.now();
//        PhysicalProductDAO physicalProduct = PhysicalProductDAO.builder()
//                .productId(2L)
//                .title("Board Game")
//                .price(29.99)
//                .description("A fun board game")
//                .physicalOrDigital("Physical")
//                .postDate(postDate)
//                .count(50)
//                .build();
//
//        assertNotNull(physicalProduct);
//        assertEquals(2L, physicalProduct.getProductId());
//        assertEquals("Board Game", physicalProduct.getTitle());
//        assertEquals(29.99, physicalProduct.getPrice());
//        assertEquals("A fun board game", physicalProduct.getDescription());
//        assertEquals("Physical", physicalProduct.getPhysicalOrDigital());
//        assertEquals(postDate, physicalProduct.getPostDate());
//        assertEquals(50, physicalProduct.getCount());
//    }
//}
