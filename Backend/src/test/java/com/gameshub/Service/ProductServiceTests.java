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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

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

    @BeforeEach
    public void setup(){
        digitalProductRepository.deleteAll();
        physicalProductRepository.deleteAll();
        sellerRepository.deleteAll();
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
        Optional<DigitalProductDAO> originalDigital = digitalProductRepository.findById(1);
        assert originalDigital.isPresent();
        assert originalDigital.get().getTitle().equals("sample digital product") && originalDigital.get().getDescription().isEmpty();

        assert productService.updateDigitalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        Optional<DigitalProductDAO> newDigital = digitalProductRepository.findById(1);
        assert newDigital.isPresent();

        DigitalProductDAO updatedDigitalProduct = newDigital.get();
        assert updatedDigitalProduct.getTitle().equals("new name") && updatedDigitalProduct.getDescription().equals("new Description");
        assert !originalDigital.get().getTitle().equals(updatedDigitalProduct.getTitle()) && !originalDigital.get().getDescription().equals(updatedDigitalProduct.getDescription());

        //Physical Product
        Optional<PhysicalProductDAO> originalPhysical = physicalProductRepository.findById(1);
        assert originalPhysical.isPresent();
        assert originalPhysical.get().getTitle().equals("sample physical product") && originalPhysical.get().getDescription().isEmpty();

        assert productService.updatePhysicalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        Optional<PhysicalProductDAO> newPhysical = physicalProductRepository.findById(1);
        assert newPhysical.isPresent();

        DigitalProductDAO updatedPhysicalProduct = newDigital.get();
        assert updatedPhysicalProduct.getTitle().equals("new name") && updatedPhysicalProduct.getDescription().equals("new Description");
        assert !originalPhysical.get().getTitle().equals(updatedPhysicalProduct.getTitle()) && !originalPhysical.get().getDescription().equals(updatedPhysicalProduct.getDescription());

        //Digital Product Request
        Optional<DigitalProductRequestDAO> originalDigitalRequest = digitalProductRequestRepository.findById(1);
        assert originalDigitalRequest.isPresent();
        assert originalDigitalRequest.get().getTitle().equals("sample Game") && originalDigitalRequest.get().getDescription().isEmpty();

        assert productRequestService.updateDigitalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        Optional<DigitalProductRequestDAO> newDigitalRequest = digitalProductRequestRepository.findById(1);
        assert newDigitalRequest.isPresent();

        DigitalProductRequestDAO updatedDigitalProductRequest = newDigitalRequest.get();
        assert updatedDigitalProductRequest.getTitle().equals("new name") && updatedDigitalProductRequest.getDescription().equals("new Description");
        assert !originalDigitalRequest.get().getTitle().equals(updatedDigitalProductRequest.getTitle()) && !originalDigitalRequest.get().getDescription().equals(updatedDigitalProductRequest.getDescription());

        //Physical Product Request
        Optional<PhysicalProductRequestDAO> originalPhysicalRequest = physicalProductRequestRepository.findById(1);
        assert originalPhysicalRequest.isPresent();
        assert originalPhysicalRequest.get().getTitle().equals("sample Keyboard") && originalPhysicalRequest.get().getDescription().isEmpty();

        assert productRequestService.updatePhysicalProductByProductID(1, new ProductPatchDTO("new name", "new Description"));

        Optional<DigitalProductRequestDAO> newPhysicalRequest = digitalProductRequestRepository.findById(1);
        assert newPhysicalRequest.isPresent();

        DigitalProductRequestDAO updatedPhysicalProductRequest = newPhysicalRequest.get();
        assert updatedPhysicalProductRequest.getTitle().equals("new name") && updatedPhysicalProductRequest.getDescription().equals("new Description");
        assert !originalPhysicalRequest.get().getTitle().equals(updatedPhysicalProductRequest.getTitle()) && !originalPhysicalRequest.get().getDescription().equals(updatedPhysicalProductRequest.getDescription());
    }

}
