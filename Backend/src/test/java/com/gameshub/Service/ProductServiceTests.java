package com.gameshub.Service;


import com.gameshub.controller.DTO.ProductPatchDTO;
import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.repository.user.SellerRepository;
import com.gameshub.service.product.ProductService;
import com.gameshub.service.request.ProductRequestService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRequestService productRequestService;

    @Autowired
    DigitalProductRepository digitalProductRepository;

    @Autowired
    PhysicalProductRepository physicalProductRepository;

    @Autowired
    DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    PhysicalProductRequestRepository physicalProductRequestRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void resetAutoIncrement(String tableName) {
        jdbcTemplate.execute("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1");
    }

    @Transactional
    @BeforeEach
    public void setup(){
        digitalProductRepository.deleteAll();
        resetAutoIncrement("digital_product");
        physicalProductRepository.deleteAll();
        resetAutoIncrement("physical_product");
        digitalProductRequestRepository.deleteAll();
        resetAutoIncrement("digital_product_request");
        physicalProductRequestRepository.deleteAll();
        resetAutoIncrement("physical_product_request");
        sellerRepository.deleteAll();
        resetAutoIncrement("seller");

        sellerRepository.save(
                new SellerDAO(
                        "Marwan",
                        "m@example.com",
                        "placeholderPassword",
                        "",
                        "",
                        0.0f,
                        "",
                        LocalDate.now(),
                        "",
                        "")
        );
        digitalProductRepository.save(
                new DigitalProductDAO(
                        1,
                        "sample digital product",
                        10,
                        "",
                        LocalDate.now(),
                        20,
                        1,
                        "",
                        "Game"
                )
        );

        physicalProductRepository.save(
                new PhysicalProductDAO(
                        1,
                        "sample physical product",
                        10,
                        "",
                        LocalDate.now(),
                        20,
                        1,
                        "Mouse"
                )
        );

        digitalProductRequestRepository.save(
                new DigitalProductRequestDAO(
                        1,
                        LocalDate.now(),
                        "Pending",
                        "Create",
                        "sample Game",
                        10,
                        "",
                        LocalDate.now(),
                        20,
                        1,
                        "Game",
                        "abcde"
                )
        );

        physicalProductRequestRepository.save(
                new PhysicalProductRequestDAO(
                        1,
                        LocalDate.now(),
                        "Pending",
                        "Create",
                        "sample Keyboard",
                        10,
                        "",
                        LocalDate.now(),
                        20,
                        1,
                        "Keyboard"
                )
        );

    }

    @Test
    public void getProduct(){
        //Digital Product
        DigitalProductDAO originalDigital = productService.getDigitalProductByProductID(1);
        assert originalDigital.getTitle().equals("sample digital product") && originalDigital.getDescription().isEmpty();

        assert productService.getDigitalProductByProductID(2).toString().equals(new DigitalProductDAO().toString());

        //Physical Product
        PhysicalProductDAO originalPhysical = productService.getPhysicalProductByProductID(1);
        assert originalPhysical.getTitle().equals("sample physical product") && originalPhysical.getDescription().isEmpty();

        assert productService.getPhysicalProductByProductID(2).toString().equals(new PhysicalProductDAO().toString());

        //Digital Product Request
        DigitalProductRequestDAO originalDigitalRequest = productRequestService.getDigitalProductByProductID(1);
        assert originalDigitalRequest.getTitle().equals("sample Game") && originalDigitalRequest.getDescription().isEmpty();

        assert productRequestService.getDigitalProductByProductID(2).toString().equals(new DigitalProductRequestDAO().toString());

        //Physical Product Request
        PhysicalProductRequestDAO originalPhysicalRequest = productRequestService.getPhysicalProductByProductID(1);
        assert originalPhysicalRequest.getTitle().equals("sample Keyboard") && originalPhysicalRequest.getDescription().isEmpty();

        assert productRequestService.getPhysicalProductByProductID(2).toString().equals(new PhysicalProductRequestDAO().toString());
    }

    @Test
    public void deleteProduct(){
        assert !productService.deleteDigitalProductBySellerIdAndProductID(1, 2);
        assert productService.deleteDigitalProductBySellerIdAndProductID(1, 1);
        assert !productService.deletePhysicalProductBySellerIdAndProductID(1, 2);
        assert productService.deletePhysicalProductBySellerIdAndProductID(1, 1);
        assert !productRequestService.deleteDigitalProductBySellerIdAndProductID(1, 2);
        assert productRequestService.deleteDigitalProductBySellerIdAndProductID(1, 1);
        assert !productRequestService.deletePhysicalProductBySellerIdAndProductID(1, 2);
        assert productRequestService.deletePhysicalProductBySellerIdAndProductID(1, 1);
    }

    @Test
    public void editProduct(){
        //Digital Product
        DigitalProductDAO originalDigital = productService.getDigitalProductByProductID(1);
        assert originalDigital.getTitle().equals("sample digital product") && originalDigital.getDescription().isEmpty();

        assert productService.updateDigitalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        DigitalProductDAO updatedDigitalProduct = productService.getDigitalProductByProductID(1);

        assert updatedDigitalProduct.getTitle().equals("new name") && updatedDigitalProduct.getDescription().equals("new Description");
        assert !originalDigital.getTitle().equals(updatedDigitalProduct.getTitle()) && !originalDigital.getDescription().equals(updatedDigitalProduct.getDescription());

        //Physical Product
        PhysicalProductDAO originalPhysical = productService.getPhysicalProductByProductID(1);
        assert originalPhysical.getTitle().equals("sample physical product") && originalPhysical.getDescription().isEmpty();

        assert productService.updatePhysicalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        PhysicalProductDAO updatedPhysicalProduct = productService.getPhysicalProductByProductID(1);

        assert updatedPhysicalProduct.getTitle().equals("new name") && updatedPhysicalProduct.getDescription().equals("new Description");
        assert !originalPhysical.getTitle().equals(updatedPhysicalProduct.getTitle()) && !originalPhysical.getDescription().equals(updatedPhysicalProduct.getDescription());

        //Digital Product Request
        DigitalProductRequestDAO originalDigitalRequest = productRequestService.getDigitalProductByProductID(1);
        assert originalDigitalRequest.getTitle().equals("sample Game") && originalDigitalRequest.getDescription().isEmpty();

        assert productRequestService.updateDigitalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        DigitalProductRequestDAO updatedDigitalProductRequest = productRequestService.getDigitalProductByProductID(1);

        assert updatedDigitalProductRequest.getTitle().equals("new name") && updatedDigitalProductRequest.getDescription().equals("new Description");
        assert !originalDigitalRequest.getTitle().equals(updatedDigitalProductRequest.getTitle()) && !originalDigitalRequest.getDescription().equals(updatedDigitalProductRequest.getDescription());

        //Physical Product Request
        PhysicalProductRequestDAO originalPhysicalRequest = productRequestService.getPhysicalProductByProductID(1);
        assert originalPhysicalRequest.getTitle().equals("sample Keyboard") && originalPhysicalRequest.getDescription().isEmpty();

        assert productRequestService.updatePhysicalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        PhysicalProductRequestDAO updatedPhysicalProductRequest = productRequestService.getPhysicalProductByProductID(1);

        assert updatedPhysicalProductRequest.getTitle().equals("new name") && updatedPhysicalProductRequest.getDescription().equals("new Description");
        assert !originalPhysicalRequest.getTitle().equals(updatedPhysicalProductRequest.getTitle()) && !originalPhysicalRequest.getDescription().equals(updatedPhysicalProductRequest.getDescription());
    }

}
