package com.gameshub.repository.product;

import com.gameshub.model.product.PhysicalImageDAO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhysicalImageRepository extends JpaRepository<PhysicalImageDAO, Integer> {
    @Query("SELECT p.image FROM PhysicalImageDAO p WHERE p.product_id = :productId")
    Optional<List<byte[]>> findAllByProduct_id(@Param("productId") int product_id);

//    @Query(value = "SELECT p.image FROM physical_product_image WHERE p.physical_product_id = :product_id LIMIT 1", nativeQuery = true)
//    Optional<byte[]> findByProduct_id(@Param("productId") int product_id);

    @Query(value = "SELECT p.image FROM physical_product_image p WHERE p.physical_product_id = :product_id LIMIT 1", nativeQuery = true)
    Optional<byte[]> findByProduct_id(@Param("product_id") int product_id);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE physical_product_image ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
