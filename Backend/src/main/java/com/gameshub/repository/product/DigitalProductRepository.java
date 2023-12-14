package com.gameshub.repository.product;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.product.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {

    List<DigitalProductDAO> findBySellerID(int sellerId);
    boolean existsBySellerID(int sellerId);
    Optional<DigitalProductDAO> findById(int id);

    @Transactional
    long deleteByIdAndSellerID(int id, int sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE DigitalProductDAO p SET p.title = :#{#patch.title}, p.description = :#{#patch.description} WHERE p.id = :id")
    int updateById(@Param("id") int id, @Param("patch") ProductPatchDTO patch);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE digitalproduct ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}