package com.gameshub.repository.user;

import com.gameshub.model.user.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerDAO, Integer> {

    Optional<BuyerDAO> findById(int id);
    Optional<BuyerDAO> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE buyer ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}