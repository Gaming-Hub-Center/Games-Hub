package com.gameshub.repository.product;

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

//    @Query(value = "SELECT p.image FROM digital_product_image WHERE p.digital_product_id = :product_id LIMIT 1", nativeQuery = true)
//    Optional<byte[]> findByProduct_id(@Param("productId") int product_id);

    @Query(value = "SELECT p.image FROM digital_product_image p WHERE p.digital_product_id = :product_id LIMIT 1", nativeQuery = true)
    Optional<byte[]> findByProduct_id(@Param("product_id") int product_id);


    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE digital_product_image ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}
