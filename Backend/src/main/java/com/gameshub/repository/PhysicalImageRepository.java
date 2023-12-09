package com.gameshub.repository;

import com.gameshub.model.product.PhysicalImageDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhysicalImageRepository extends JpaRepository<PhysicalImageDAO, Integer> {
    @Query("SELECT p.image FROM PhysicalImageDAO p WHERE p.product_id = :productId")
    Optional<List<byte[]>> findAllByProduct_id(@Param("productId") int product_id);

    /*@Query("SELECT p.image FROM PhysicalImageDAO p WHERE p.product_id = :productId LIMIT 1")
    Optional<byte[]> findFirstByProduct_id(@Param("productId") int product_id);*/
   /*@Query("SELECT p FROM PhysicalImageDAO p WHERE p.product_id = :productId")
    Optional<PhysicalImageDAO> findFirstByProduct_id(@Param("productId") int product_id);*/
}
