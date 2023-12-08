package com.gameshub.DataAccessLayer;

import com.gameshub.model.user.*;
import com.gameshub.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.crypto.password.*;

import java.time.*;
import java.util.*;

@SpringBootTest
public class SellerRepositoryTest {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        sellerRepository.deleteAll();
        SellerDAO sellerDAO = new SellerDAO("Alice Blue", "alice.blue@example.com", "$2a$10$HaID.XdQm../yady9rA2k.EoY4oiL/In32c/cLRa3DWyW/Nn6DXcG", "+1029384756", "101 Red Street", 10000, "ID12345A", LocalDate.parse("2023-01-01"), "Description about Alice", "123456789A");
        sellerRepository.save(sellerDAO);
    }

    @Test
    public void findByEmailTests() {
        assert sellerRepository.findByEmail("rfa3y@eldso2y.com").isEmpty();
        Optional<SellerDAO> sellerDAOOptional = sellerRepository.findByEmail("alice.blue@example.com");
        assert sellerDAOOptional.isPresent();
        SellerDAO sellerDAO = sellerDAOOptional.get();
        assert sellerDAO.getEmail().equals("alice.blue@example.com");
        assert sellerDAO.getName().equals("Alice Blue");
        assert passwordEncoder.matches("alicepass", sellerDAO.getPassword());
        assert sellerDAO.getPhone().equals("+1029384756");
    }

    @Test
    public void existsByEmailTests(){
        assert sellerRepository.existsByEmail("alice.blue@example.com");
        assert !sellerRepository.existsByEmail("rfa3y@eldso2y.com");
    }

}