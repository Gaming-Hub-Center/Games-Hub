package com.gameshub.repository.product;

import com.gameshub.controller.DTO.ProductPatchDTO;
import com.gameshub.model.product.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.List;
import java.util.Optional;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {

    List<DigitalProductDAO> findBySellerId(int sellerId);
    boolean existsBySellerId(int sellerId);
    Optional<DigitalProductDAO> findById(int id);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE DigitalProductDAO p SET p.title = :#{#patch.title}, p.description = :#{#patch.description} WHERE p.id = :id")
    int updateById(@Param("id") int id, @Param("patch") ProductPatchDTO patch);


    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE digitalproduct ALTER COLUMN Id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}