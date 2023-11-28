package com.gameshub.DataAccessLayer;

import com.gameshub.Model.Users.BuyerDAO;
import com.gameshub.Repository.BuyerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootTest
public class BuyerRepositoryTests {

    @Autowired
    BuyerRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByEmailTests(){
        assert repo.findByEmail("rfa3y@eldso2y.com").isEmpty();
        Optional<BuyerDAO> buyerDAOOptional= repo.findByEmail("john.doe@example.com");
        assert buyerDAOOptional.isPresent();
        BuyerDAO buyerEntity = buyerDAOOptional.get();
        assert buyerEntity.getEmail().equals("john.doe@example.com");
        assert buyerEntity.getName().equals("John Doe");
        assert passwordEncoder.matches("password123" ,buyerEntity.getPassword());
        assert buyerEntity.getPhone().equals("+1234567890");
    }

    @Test
    public void existsByNameTests(){
        assert repo.existsByName("John Doe");
        assert !repo.existsByName("Rfa3y Eldso2y");
    }

    @Test
    public void existsByEmailTests(){
        assert repo.existsByEmail("john.doe@example.com");
        assert !repo.existsByEmail("rfa3y@eldso2y.com");
    }
}
