package com.gameshub.repository;

import com.gameshub.model.product.DigitalImageDAO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DigitalImageRepository extends JpaRepository<DigitalImageDAO, Integer> {
    @Query("SELECT d.image FROM DigitalImageDAO d WHERE d.product_id = :productId")
    Optional<List<byte[]>> findAllByProduct_id(@Param("productId") int product_id);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE digital_product_image ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}
