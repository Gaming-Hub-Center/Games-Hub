package com.gameshub.Service;


import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.product.DigitalProductRepository;
import com.gameshub.repository.product.PhysicalProductRepository;
import com.gameshub.repository.user.SellerRepository;
import com.gameshub.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class ProductServiceTests {

    @Autowired
    ProductService productService;

    @Autowired
    DigitalProductRepository digitalProductRepository;

    @Autowired
    PhysicalProductRepository physicalProductRepository;

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

    }
}
