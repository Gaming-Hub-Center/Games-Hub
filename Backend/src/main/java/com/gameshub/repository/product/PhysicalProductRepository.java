package com.gameshub.repository.product;

import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.product.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProductDAO, Integer> {

    List<PhysicalProductDAO> findBySellerID(int sellerId);
    boolean existsBySellerID(int sellerId);
    Optional<PhysicalProductDAO> findById(int id);

    @Transactional
    long deleteByIdAndSellerID(int id, int sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE PhysicalProductDAO p SET p.title = :#{#patch.title}, p.description = :#{#patch.description} WHERE p.id = :id")
    int updateById(@Param("id") int id, @Param("patch") ProductPatchDTO patch);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE physicalproduct ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    Optional<PhysicalProductDAO> findById(Integer ID);

    @Query("SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title, p.description) FROM PhysicalProductDAO p")
    Optional<List<ProductBriefDTO>> findAllProducts();

    @Query("SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title, p.description) FROM PhysicalProductDAO p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
    Optional<List<ProductBriefDTO>> findAllByTitleContainingIgnoreCase(@Param("key") String key);

    @Query(value = "SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title, p.description) " +
            "FROM PhysicalProductDAO p " +
            "WHERE (p.price >= :lowerBound) " +
            "AND (p.price < :upperBound) " +
            "AND (:category IS NULL OR LOWER(p.category) = :category)")
    List<ProductBriefDTO> filterPhysical(
            @Param("lowerBound") float lowerBound,
            @Param("upperBound") float upperBound,
            @Param("category") String category
    );

    @Query("SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title, p.description) " +
            "FROM PhysicalProductDAO p ORDER BY p.price ASC")
    Optional<List<ProductBriefDTO>> getOrderedByPrice();

}