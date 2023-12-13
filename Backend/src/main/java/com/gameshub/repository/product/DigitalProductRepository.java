package com.gameshub.repository.product;

import com.gameshub.model.product.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE digitalproduct ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}