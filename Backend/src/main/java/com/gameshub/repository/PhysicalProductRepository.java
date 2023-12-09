package com.gameshub.repository;

import com.gameshub.controller.DTO.ProductBriefDTO;
import com.gameshub.model.product.PhysicalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProductDAO, Integer> {
    Optional<PhysicalProductDAO> findById(Integer ID);
   /* @Query("SELECT p.id, p.price, p.title FROM PhysicalProductDAO p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
    Optional<List<ProductBriefDTO>> findAllByTitleContainingIgnoreCase(@Param("key") String key);*/

    @Query("SELECT new com.gameshub.controller.DTO.ProductBriefDTO(p.id, p.price, p.title) FROM PhysicalProductDAO p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
    Optional<List<ProductBriefDTO>> findAllByTitleContainingIgnoreCase(@Param("key") String key);
}
