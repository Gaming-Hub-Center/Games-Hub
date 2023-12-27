package com.gameshub;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.request.image.DigitalProductRequestImage;
import com.gameshub.model.request.image.PhysicalProductRequestImage;
import com.gameshub.model.user.SellerDAO;
import com.gameshub.repository.request.DigitalProductRequestImageRepository;
import com.gameshub.repository.request.DigitalProductRequestRepository;
import com.gameshub.repository.request.PhysicalProductRequestImageRepository;
import com.gameshub.repository.request.PhysicalProductRequestRepository;
import com.gameshub.repository.user.SellerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ImageSaveTest {
    @Autowired
    private DigitalProductRequestImageRepository digitalImageRepo;

    @Autowired
    private PhysicalProductRequestImageRepository physicalImageRepo;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private DigitalProductRequestRepository digitalProductRequestRepository;

    @Autowired
    private PhysicalProductRequestRepository physicalProductRequestRepository;

    private DigitalProductRequestDAO digitalProductRequestDAO;
    private PhysicalProductRequestDAO physicalProductRequestDAO;

    @BeforeEach
    public void setup() {
        SellerDAO sellerDAO = new SellerDAO("name", "email", "password", "phone", "address", 12, "nationalID", LocalDate.now(), "sellerDescription", "vatRegistrationNumber");
        sellerRepository.save(sellerDAO);

        SellerDAO savedSeller = sellerRepository.findByEmail("email").orElse(null);

        digitalProductRequestDAO = new DigitalProductRequestDAO(LocalDate.now(), "Pending", "Game", "Test Game", 59, "Test Description", LocalDate.now(), 100, "Action", savedSeller, "Code");
        digitalProductRequestRepository.save(digitalProductRequestDAO);

        physicalProductRequestDAO = new PhysicalProductRequestDAO(LocalDate.now(), "Pending", "Game", "Test Game", 59, "Test Description", LocalDate.now(), 100, "Action", savedSeller);
        physicalProductRequestRepository.save(physicalProductRequestDAO);
    }

    @Test
    @Transactional
    public void testSaveImage() {
        List<DigitalProductRequestImage> digitalImages = new ArrayList<>();
        digitalImages.add(new DigitalProductRequestImage("http://example.com/digital1.jpg", digitalProductRequestDAO));
        digitalImages.add(new DigitalProductRequestImage("http://example.com/digital2.jpg", digitalProductRequestDAO));
        digitalImages.add(new DigitalProductRequestImage("http://example.com/digital3.jpg", digitalProductRequestDAO));
        digitalImageRepo.saveAll(digitalImages);

        List<PhysicalProductRequestImage> physicalImages = new ArrayList<>();
        physicalImages.add(new PhysicalProductRequestImage("http://example.com/physical1.jpg", physicalProductRequestDAO));
        physicalImages.add(new PhysicalProductRequestImage("http://example.com/physical2.jpg", physicalProductRequestDAO));
        physicalImages.add(new PhysicalProductRequestImage("http://example.com/physical3.jpg", physicalProductRequestDAO));
        physicalImageRepo.saveAll(physicalImages);

        List<PhysicalProductRequestImage> retrievedProductRequestImages = physicalImageRepo.findAll();
        List<DigitalProductRequestImage> retrievedProductRequestImages_ = digitalImageRepo.findAll();
        List<DigitalProductRequestImage> retrievedProductRequestImagesByProduct = digitalImageRepo.findByDigitalProductRequest(digitalProductRequestDAO);
        List<PhysicalProductRequestImage> retrievedProductRequestImagesByProduct_ = physicalImageRepo.findByPhysicalProductRequest(physicalProductRequestDAO);

        Assertions.assertEquals(retrievedProductRequestImages_.size(), 3);
        Assertions.assertEquals(retrievedProductRequestImages.size(), 3);
        Assertions.assertEquals(retrievedProductRequestImagesByProduct.size(), 3);
        Assertions.assertEquals(retrievedProductRequestImagesByProduct_.size(), 3);
    }

}
