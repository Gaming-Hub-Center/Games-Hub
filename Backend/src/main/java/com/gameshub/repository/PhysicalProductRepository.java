package com.gameshub.repository;

import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.model.product.PhysicalProductDAO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProductDAO, Integer> {
    Optional<PhysicalProductDAO> findById(Integer ID);

    @Query("SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title) FROM PhysicalProductDAO p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
    Optional<List<ProductBriefDTO>> findAllByTitleContainingIgnoreCase(@Param("key") String key);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE physical_product ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    @Query(value = "SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title) " +
            "FROM PhysicalProductDAO p " +
            "WHERE (p.price >= :lowerBound) " +
            "AND (p.price < :upperBound) " +
            "AND (:category IS NULL OR LOWER(p.category) = :category)")
    List<ProductBriefDTO> filterPhysical(
            @Param("lowerBound") float lowerBound,
            @Param("upperBound") float upperBound,
            @Param("category") String category
    );

    @Query("SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title) " +
            "FROM PhysicalProductDAO p ORDER BY p.price ASC")
    Optional<List<ProductBriefDTO>> getOrderedByPrice();
}