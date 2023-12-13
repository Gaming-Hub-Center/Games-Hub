package com.gameshub.DataAccessLayer;

import com.gameshub.model.user.*;
import com.gameshub.repository.user.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.crypto.password.*;

import java.util.*;

@SpringBootTest
public class BuyerRepositoryTests {

    @Autowired
    BuyerRepository buyerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        buyerRepository.deleteAll();
        BuyerDAO buyerDAO = new BuyerDAO("John Doe", "john.doe@example.com", "$2a$10$YJGLrNDJ0F.mE2E6IFWnDeDrkKlvQ3FuSYaOiUieGjTMkraZJoRBG", "+1234567890", "123 Elm Street", 1000);
        buyerRepository.save(buyerDAO);
    }

    @Test
    public void findByEmailTests() {
        assert buyerRepository.findByEmail("rfa3y@eldso2y.com").isEmpty();
        Optional<BuyerDAO> buyerDAOOptional = buyerRepository.findByEmail("john.doe@example.com");
        assert buyerDAOOptional.isPresent();
        BuyerDAO buyerDAO = buyerDAOOptional.get();
        assert buyerDAO.getEmail().equals("john.doe@example.com");
        assert buyerDAO.getName().equals("John Doe");
        assert passwordEncoder.matches("password123", buyerDAO.getPassword());
        assert buyerDAO.getPhone().equals("+1234567890");
    }

    @Test
    public void existsByEmailTests(){
        assert buyerRepository.existsByEmail("john.doe@example.com");
        assert !buyerRepository.existsByEmail("rfa3y@eldso2y.com");
    }

}