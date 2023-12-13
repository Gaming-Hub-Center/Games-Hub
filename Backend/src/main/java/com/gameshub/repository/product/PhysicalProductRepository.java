package com.gameshub.repository.product;

import com.gameshub.model.product.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProductDAO, Integer> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE physicalproduct ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}