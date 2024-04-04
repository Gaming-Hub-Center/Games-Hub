<<<<<<< Updated upstream
package com.gameshub.service;

import com.gameshub.controller.DTO.*;
import com.gameshub.exception.ResourceNotFoundException;
import com.gameshub.model.product.*;
import com.gameshub.repository.product.DigitalImageRepository;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalImageRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@SpringBootTest
public class ProductServiceSortTests {
    @Autowired
    private ProductService productService;

    @Autowired
    private PhysicalProductRepository physicalProductRepository;

    @Autowired
    private PhysicalImageRepository physicalImageRepository;

    @Autowired
    private DigitalProductRepository digitalProductRepository;

    @Autowired
    private DigitalImageRepository digitalImageRepository;

    @BeforeEach
    public void setup() {
        physicalProductRepository.deleteAll();
        physicalImageRepository.deleteAll();
        digitalProductRepository.deleteAll();
        digitalImageRepository.deleteAll();

        physicalProductRepository.resetAutoIncrement();
        physicalImageRepository.resetAutoIncrement();
        digitalProductRepository.resetAutoIncrement();
        digitalImageRepository.resetAutoIncrement();

        physicalProductRepository.save(new PhysicalProductDAO("physical test title1", 100, "physical test decription1", 2, 96, "pc", LocalDate.parse("2023-12-08")));
        physicalProductRepository.save(new PhysicalProductDAO("physical test title2", 200, "physical test decription2", 2, 96, "mouse", LocalDate.parse("2023-12-08")));
        physicalProductRepository.save(new PhysicalProductDAO("physical test title3", 300, "physical test decription3", 2, 96, "keyboard", LocalDate.parse("2023-12-08")));

        physicalImageRepository.save(new PhysicalImageDAO(1, new byte[]{1, 2, 3}, 1));
        physicalImageRepository.save(new PhysicalImageDAO(2, new byte[]{4, 5, 6}, 2));
        physicalImageRepository.save(new PhysicalImageDAO(3, new byte[]{7, 8, 9}, 3));

        digitalProductRepository.save(new DigitalProductDAO("digital test title1", 100, "digital test decription1", 2, 96, "action", "code1", LocalDate.parse("2023-12-08")));
        digitalProductRepository.save(new DigitalProductDAO("digital test title2", 200, "digital test decription2", 2, 96, "arcade", "code2", LocalDate.parse("2023-12-08")));
        digitalProductRepository.save(new DigitalProductDAO("digital test title3", 300, "digital test decription3", 2, 96, "sport", "code3", LocalDate.parse("2023-12-08")));

        digitalImageRepository.save(new DigitalImageDAO(1, new byte[]{1, 2, 3}, 1));
        digitalImageRepository.save(new DigitalImageDAO(2, new byte[]{4, 5, 6}, 2));
        digitalImageRepository.save(new DigitalImageDAO(3, new byte[]{7, 8, 9}, 3));
    }


    @Test
    public void testGetPhysicalByID() {
        PhysicalProductDTO result = productService.getPhysicalByID(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(100, result.getPrice());
        assertArrayEquals(new byte[]{1, 2, 3}, result.getImages().get(0));
        assertEquals("physical test decription1", result.getDescription());
        assertEquals("physical test title1", result.getTitle());
        assertEquals(96, result.getCount());
        assertEquals(2, result.getSellerID());
        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
    }

    @Test
    public void testGetPhysicalByID2() {
        PhysicalProductDTO result = productService.getPhysicalByID(2);

        assertNotNull(result);
        assertEquals(2, result.getId());
        assertEquals(200, result.getPrice());
        assertArrayEquals(new byte[]{4, 5, 6}, result.getImages().get(0));
        assertEquals("physical test decription2", result.getDescription());
        assertEquals("physical test title2", result.getTitle());
        assertEquals(96, result.getCount());
        assertEquals(2, result.getSellerID());
        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
    }

    @Test
    public void testGetPhysicalByID3() {
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getPhysicalByID(4);
        });
    }

    @Test
    public void testGetDigitalByID() {
        DigitalProductDTO result = productService.getDigitalByID(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals(100, result.getPrice());
        assertArrayEquals(new byte[]{1, 2, 3}, result.getImages().get(0));
        assertEquals("digital test decription1", result.getDescription());
        assertEquals("digital test title1", result.getTitle());
        assertEquals(96, result.getCount());
        assertEquals(2, result.getSellerID());
        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
    }

    @Test
    public void testGetDigitalByID2() {
        DigitalProductDTO result = productService.getDigitalByID(2);

        assertNotNull(result);
        assertEquals(2, result.getId());
        assertEquals(200, result.getPrice());
        assertArrayEquals(new byte[]{4, 5, 6}, result.getImages().get(0));
        assertEquals("digital test decription2", result.getDescription());
        assertEquals("digital test title2", result.getTitle());
        assertEquals(96, result.getCount());
        assertEquals(2, result.getSellerID());
        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
    }

    @Test
    public void testGetDigitalByID3() {
        assertThrows(ResourceNotFoundException.class, () -> {
            productService.getDigitalByID(4);
        });
    }

    @Test
    public void testPhysicalSearch() {
        List<ProductBriefDTO> result = productService.searchPhysicalByTitle("title1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(100, result.get(0).getPrice());
        assertEquals("physical test title1", result.get(0).getTitle());
        assertArrayEquals(new byte[]{1, 2, 3}, result.get(0).getImage());
    }

    @Test
    public void testPhysicalSearch2() {
        List<ProductBriefDTO> result = productService.searchPhysicalByTitle("title16");

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testDigitalSearch() {
        List<ProductBriefDTO> result = productService.searchDigitalByTitle("title1");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(100, result.get(0).getPrice());
        assertEquals("digital test title1", result.get(0).getTitle());
        assertArrayEquals(new byte[]{1, 2, 3}, result.get(0).getImage());
    }

    @Test
    public void testDigitalSearch2() {
        List<ProductBriefDTO> result = productService.searchDigitalByTitle("title16");

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testFilterPhysicalByPrice() {
        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, null);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
        for (int i = 0; i < results.size(); i++ ) {
            ProductBriefDTO product = results.get(i);
            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
            assertEquals(i+1, product.getId());
            assertArrayEquals(new byte[]{(byte) (3*i+1), (byte) (3*i+2), (byte) (3*i+3)}, product.getImage());
        }
    }

    @Test
    public void testFilterPhysicalByPrice2() {
        List<ProductBriefDTO> results = productService.filterPhysical(600F, 300F, null);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testFilterDigitalByPrice() {
        List<ProductBriefDTO> results = productService.filterDigital(100F, 250F, null);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
        for (int i = 0; i < results.size(); i++ ) {
            ProductBriefDTO product = results.get(i);
            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
            assertEquals(i+1, product.getId());
            assertArrayEquals(new byte[]{(byte) (3*i+1), (byte) (3*i+2), (byte) (3*i+3)}, product.getImage());
        }
    }

    @Test
    public void testFilterDigitalByPrice2() {
        List<ProductBriefDTO> results = productService.filterDigital(600F, 300F, null);

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testFilterPhysicalByPriceAndCategory() {
        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, "Pc");

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        for (int i = 0; i < results.size(); i++ ) {
            ProductBriefDTO product = results.get(i);
            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
            assertEquals(i+1, product.getId());
            assertArrayEquals(new byte[]{(byte) (3*i+1), (byte) (3*i+2), (byte) (3*i+3)}, product.getImage());
        }
    }

    @Test
    public void testFilterPhysicalByPriceAndCategory2() {
        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, "laptop");

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    public void testFilterDigitalByPriceAndCategory() {
        List<ProductBriefDTO> results = productService.filterDigital(100F, 250F, "AcTIoN");

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        for (int i = 0; i < results.size(); i++ ) {
            ProductBriefDTO product = results.get(i);
            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
            assertEquals(i+1, product.getId());
            assertArrayEquals(new byte[]{(byte) (3*i+1), (byte) (3*i+2), (byte) (3*i+3)}, product.getImage());
        }
    }

    @Test
    public void testFilterDigitalByPriceAndCategory2() {
        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, "laptop");

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    void testSortPhysicalAscending() {
        List<ProductBriefDTO> result = productService.sortPhysical(true);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(3, result.get(2).getId());
        assertEquals(100, result.get(0).getPrice());
        assertEquals(200, result.get(1).getPrice());
        assertEquals(300, result.get(2).getPrice());
    }

    @Test
    void testSortPhysicalDescending() {
        List<ProductBriefDTO> result = productService.sortPhysical(false);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals(3, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(2).getId());
        assertEquals(300, result.get(0).getPrice());
        assertEquals(200, result.get(1).getPrice());
        assertEquals(100, result.get(2).getPrice());
    }

    @Test
    void testSortDigitalAscending() {
        List<ProductBriefDTO> result = productService.sortDigital(true);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(3, result.get(2).getId());
        assertEquals(100, result.get(0).getPrice());
        assertEquals(200, result.get(1).getPrice());
        assertEquals(300, result.get(2).getPrice());
    }

    @Test
    void testSortDigitalDescending() {
        List<ProductBriefDTO> result = productService.sortDigital(false);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertEquals(3, result.get(0).getId());
        assertEquals(2, result.get(1).getId());
        assertEquals(1, result.get(2).getId());
        assertEquals(300, result.get(0).getPrice());
        assertEquals(200, result.get(1).getPrice());
        assertEquals(100, result.get(2).getPrice());
    }
}
=======
//package com.gameshub.service;
//
//import com.gameshub.dto.product.DigitalProductDTO;
//import com.gameshub.dto.product.PhysicalProductDTO;
//import com.gameshub.dto.product.ProductBriefDTO;
//import com.gameshub.exception.ResourceNotFoundException;
//import com.gameshub.model.product.*;
//import com.gameshub.repository.product.DigitalImageRepository;
//import com.gameshub.repository.product.DigitalProductRepository;
//import com.gameshub.repository.product.PhysicalImageRepository;
//import com.gameshub.repository.product.PhysicalProductRepository;
//import com.gameshub.service.product.ProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.time.LocalDate;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//
//
//@SpringBootTest
//public class ProductServiceSortTests {
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private PhysicalProductRepository physicalProductRepository;
//
//    @Autowired
//    private PhysicalImageRepository physicalImageRepository;
//
//    @Autowired
//    private DigitalProductRepository digitalProductRepository;
//
//    @Autowired
//    private DigitalImageRepository digitalImageRepository;
//
//    @BeforeEach
//    public void setup() {
//        physicalProductRepository.deleteAll();
//        physicalImageRepository.deleteAll();
//        digitalProductRepository.deleteAll();
//        digitalImageRepository.deleteAll();
//
//        physicalProductRepository.resetAutoIncrement();
//        physicalImageRepository.resetAutoIncrement();
//        digitalProductRepository.resetAutoIncrement();
//        digitalImageRepository.resetAutoIncrement();
//
//        physicalProductRepository.save(new PhysicalProductDAO("physical test title1", 100, "physical test decription1", 2, 96, "pc", LocalDate.parse("2023-12-08")));
//        physicalProductRepository.save(new PhysicalProductDAO("physical test title2", 200, "physical test decription2", 2, 96, "mouse", LocalDate.parse("2023-12-08")));
//        physicalProductRepository.save(new PhysicalProductDAO("physical test title3", 300, "physical test decription3", 2, 96, "keyboard", LocalDate.parse("2023-12-08")));
//
//        physicalImageRepository.save(new PhysicalImageDAO(1, "123", 1));
//        physicalImageRepository.save(new PhysicalImageDAO(2, "456", 2));
//        physicalImageRepository.save(new PhysicalImageDAO(3, "789", 3));
//
//        digitalProductRepository.save(new DigitalProductDAO("digital test title1", 100, "digital test decription1", 2, 96, "action", "code1", LocalDate.parse("2023-12-08")));
//        digitalProductRepository.save(new DigitalProductDAO("digital test title2", 200, "digital test decription2", 2, 96, "arcade", "code2", LocalDate.parse("2023-12-08")));
//        digitalProductRepository.save(new DigitalProductDAO("digital test title3", 300, "digital test decription3", 2, 96, "sport", "code3", LocalDate.parse("2023-12-08")));
//
//        digitalImageRepository.save(new DigitalImageDAO(1, "123", 1));
//        digitalImageRepository.save(new DigitalImageDAO(2, "456", 2));
//        digitalImageRepository.save(new DigitalImageDAO(3, "789", 3));
//    }
//
//
//    @Test
//    public void testGetPhysicalByID() {
//        PhysicalProductDTO result = productService.getPhysicalByID(1);
//
//        assertNotNull(result);
//        assertEquals(1, result.getId());
//        assertEquals(100, result.getPrice());
//        assertEquals("123", result.getUrls().get(0));
//        assertEquals("physical test decription1", result.getDescription());
//        assertEquals("physical test title1", result.getTitle());
//        assertEquals(96, result.getCount());
//        assertEquals(2, result.getSellerID());
//        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
//    }
//
//    @Test
//    public void testGetPhysicalByID2() {
//        PhysicalProductDTO result = productService.getPhysicalByID(2);
//
//        assertNotNull(result);
//        assertEquals(2, result.getId());
//        assertEquals(200, result.getPrice());
//        assertEquals("456", result.getUrls().get(0));
//        assertEquals("physical test decription2", result.getDescription());
//        assertEquals("physical test title2", result.getTitle());
//        assertEquals(96, result.getCount());
//        assertEquals(2, result.getSellerID());
//        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
//    }
//
//    @Test
//    public void testGetPhysicalByID3() {
//        assertThrows(ResourceNotFoundException.class, () -> {
//            productService.getPhysicalByID(4);
//        });
//    }
//
//    @Test
//    public void testGetDigitalByID() {
//        DigitalProductDTO result = productService.getDigitalByID(1);
//
//        assertNotNull(result);
//        assertEquals(1, result.getId());
//        assertEquals(100, result.getPrice());
//        assertEquals("123", result.getUrls().get(0));
//        assertEquals("digital test decription1", result.getDescription());
//        assertEquals("digital test title1", result.getTitle());
//        assertEquals(96, result.getCount());
//        assertEquals(2, result.getSellerID());
//        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
//    }
//
//    @Test
//    public void testGetDigitalByID2() {
//        DigitalProductDTO result = productService.getDigitalByID(2);
//
//        assertNotNull(result);
//        assertEquals(2, result.getId());
//        assertEquals(200, result.getPrice());
//        assertEquals("456", result.getUrls().get(0));
//        assertEquals("digital test decription2", result.getDescription());
//        assertEquals("digital test title2", result.getTitle());
//        assertEquals(96, result.getCount());
//        assertEquals(2, result.getSellerID());
//        assertEquals(LocalDate.parse("2023-12-08"), result.getPostDate());
//    }
//
//    @Test
//    public void testGetDigitalByID3() {
//        assertThrows(ResourceNotFoundException.class, () -> {
//            productService.getDigitalByID(4);
//        });
//    }
//
//    @Test
//    public void testPhysicalSearch() {
//        List<ProductBriefDTO> result = productService.searchPhysicalByTitle("title1");
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(1, result.get(0).getId());
//        assertEquals(100, result.get(0).getPrice());
//        assertEquals("physical test title1", result.get(0).getTitle());
//        assertEquals("123", result.get(0).getUrl());
//    }
//
//    @Test
//    public void testPhysicalSearch2() {
//        List<ProductBriefDTO> result = productService.searchPhysicalByTitle("title16");
//
//        assertNotNull(result);
//        assertEquals(0, result.size());
//    }
//
//    @Test
//    public void testDigitalSearch() {
//        List<ProductBriefDTO> result = productService.searchDigitalByTitle("title1");
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(1, result.get(0).getId());
//        assertEquals(100, result.get(0).getPrice());
//        assertEquals("digital test title1", result.get(0).getTitle());
//        assertEquals("123", result.get(0).getUrl());
//    }
//
//    @Test
//    public void testDigitalSearch2() {
//        List<ProductBriefDTO> result = productService.searchDigitalByTitle("title16");
//
//        assertNotNull(result);
//        assertEquals(0, result.size());
//    }
//
//    @Test
//    public void testFilterPhysicalByPrice() {
//        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, null);
//
//        assertNotNull(results);
//        assertFalse(results.isEmpty());
//        assertEquals(2, results.size());
//        for (int i = 0; i < results.size(); i++ ) {
//            ProductBriefDTO product = results.get(i);
//            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
//            assertEquals(i+1, product.getId());
//            assertEquals("" + (int)(3*i+1) +  (3*i+2) + (3*i+3), product.getUrl());
//        }
//    }
//
//    @Test
//    public void testFilterPhysicalByPrice2() {
//        List<ProductBriefDTO> results = productService.filterPhysical(600F, 300F, null);
//
//        assertNotNull(results);
//        assertTrue(results.isEmpty());
//    }
//
//    @Test
//    public void testFilterDigitalByPrice() {
//        List<ProductBriefDTO> results = productService.filterDigital(100F, 250F, null);
//
//        assertNotNull(results);
//        assertFalse(results.isEmpty());
//        assertEquals(2, results.size());
//        for (int i = 0; i < results.size(); i++ ) {
//            ProductBriefDTO product = results.get(i);
//            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
//            assertEquals(i+1, product.getId());
//            assertEquals("" + (3*i+1) +  (3*i+2) + (3*i+3), product.getUrl());
//        }
//    }
//
//    @Test
//    public void testFilterDigitalByPrice2() {
//        List<ProductBriefDTO> results = productService.filterDigital(600F, 300F, null);
//
//        assertNotNull(results);
//        assertTrue(results.isEmpty());
//    }
//
//    @Test
//    public void testFilterPhysicalByPriceAndCategory() {
//        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, "Pc");
//
//        assertNotNull(results);
//        assertFalse(results.isEmpty());
//        assertEquals(1, results.size());
//        for (int i = 0; i < results.size(); i++ ) {
//            ProductBriefDTO product = results.get(i);
//            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
//            assertEquals(i+1, product.getId());
//            assertEquals("" + (3*i+1) +  (3*i+2) + (3*i+3), product.getUrl());
//        }
//    }
//
//    @Test
//    public void testFilterPhysicalByPriceAndCategory2() {
//        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, "laptop");
//
//        assertNotNull(results);
//        assertTrue(results.isEmpty());
//    }
//
//    @Test
//    public void testFilterDigitalByPriceAndCategory() {
//        List<ProductBriefDTO> results = productService.filterDigital(100F, 250F, "AcTIoN");
//
//        assertNotNull(results);
//        assertFalse(results.isEmpty());
//        assertEquals(1, results.size());
//        for (int i = 0; i < results.size(); i++ ) {
//            ProductBriefDTO product = results.get(i);
//            assertTrue(product.getPrice() >= 100 && product.getPrice() <= 250);
//            assertEquals(i+1, product.getId());
//            assertEquals("" + (3*i+1) +  (3*i+2) + (3*i+3), product.getUrl());
//        }
//    }
//
//    @Test
//    public void testFilterDigitalByPriceAndCategory2() {
//        List<ProductBriefDTO> results = productService.filterPhysical(100F, 250F, "laptop");
//
//        assertNotNull(results);
//        assertTrue(results.isEmpty());
//    }
//
//    @Test
//    void testSortPhysicalAscending() {
//        List<ProductBriefDTO> result = productService.sortPhysical(true);
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(3, result.size());
//        assertEquals(1, result.get(0).getId());
//        assertEquals(2, result.get(1).getId());
//        assertEquals(3, result.get(2).getId());
//        assertEquals(100, result.get(0).getPrice());
//        assertEquals(200, result.get(1).getPrice());
//        assertEquals(300, result.get(2).getPrice());
//    }
//
//    @Test
//    void testSortPhysicalDescending() {
//        List<ProductBriefDTO> result = productService.sortPhysical(false);
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(3, result.size());
//        assertEquals(3, result.get(0).getId());
//        assertEquals(2, result.get(1).getId());
//        assertEquals(1, result.get(2).getId());
//        assertEquals(300, result.get(0).getPrice());
//        assertEquals(200, result.get(1).getPrice());
//        assertEquals(100, result.get(2).getPrice());
//    }
//
//    @Test
//    void testSortDigitalAscending() {
//        List<ProductBriefDTO> result = productService.sortDigital(true);
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(3, result.size());
//        assertEquals(1, result.get(0).getId());
//        assertEquals(2, result.get(1).getId());
//        assertEquals(3, result.get(2).getId());
//        assertEquals(100, result.get(0).getPrice());
//        assertEquals(200, result.get(1).getPrice());
//        assertEquals(300, result.get(2).getPrice());
//    }
//
//    @Test
//    void testSortDigitalDescending() {
//        List<ProductBriefDTO> result = productService.sortDigital(false);
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(3, result.size());
//        assertEquals(3, result.get(0).getId());
//        assertEquals(2, result.get(1).getId());
//        assertEquals(1, result.get(2).getId());
//        assertEquals(300, result.get(0).getPrice());
//        assertEquals(200, result.get(1).getPrice());
//        assertEquals(100, result.get(2).getPrice());
//    }
//}
>>>>>>> Stashed changes
