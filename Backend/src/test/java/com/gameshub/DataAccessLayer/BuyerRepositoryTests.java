package com.gameshub.DataAccessLayer;

import com.gameshub.model.user.*;
import com.gameshub.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.crypto.password.*;

import java.util.*;

@SpringBootTest
public class BuyerRepositoryTests {

    @Autowired
    BuyerRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByEmailTests() {
        assert repo.findByEmail("rfa3y@eldso2y.com").isEmpty();
        Optional<BuyerDAO> buyerDAOOptional = repo.findByEmail("john.doe@example.com");
        assert buyerDAOOptional.isPresent();
        BuyerDAO buyerEntity = buyerDAOOptional.get();
        assert buyerEntity.getEmail().equals("john.doe@example.com");
        assert buyerEntity.getName().equals("John Doe");
        assert buyerEntity.getPassword().equals("password123");
        assert buyerEntity.getPhone().equals("+1234567890");
    }

    @Test
    public void existsByEmailTests(){
        assert repo.existsByEmail("john.doe@example.com");
        assert !repo.existsByEmail("rfa3y@eldso2y.com");
    }
}