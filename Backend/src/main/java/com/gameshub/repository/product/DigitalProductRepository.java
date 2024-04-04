package com.gameshub.repository.product;

import com.gameshub.dto.product.ProductBriefDTO;
import com.gameshub.dto.product.ProductPatchDTO;
import com.gameshub.model.product.DigitalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @Query(value = "ALTER TABLE digitalproduct ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    Optional<DigitalProductDAO> findById(Integer ID);

    @Query("SELECT new com.gameshub.dto.product.ProductBriefDTO(p.id, p.price, p.title, p.description) FROM DigitalProductDAO p")
    Optional<List<ProductBriefDTO>> findAllProducts();

    @Query("SELECT new com.gameshub.dto.product.ProductBriefDTO(p.id, p.price, p.title, p.description) FROM DigitalProductDAO p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :key, '%'))")
    Optional<List<ProductBriefDTO>> findAllByTitleContainingIgnoreCase(@Param("key") String key);

    @Query(value = "SELECT new com.gameshub.dto.product.ProductBriefDTO(p.id, p.price, p.title, p.description) " +
            "FROM DigitalProductDAO p " +
            "WHERE (p.price >= :lowerBound) " +
            "AND (p.price < :upperBound) " +
            "AND (:category IS NULL OR LOWER(p.category) = :category)")
    List<ProductBriefDTO> filterPhysical(
            @Param("lowerBound") float lowerBound,
            @Param("upperBound") float upperBound,
            @Param("category") String category
    );

    @Query("SELECT new com.gameshub.dto.product.ProductBriefDTO(p.id, p.price, p.title, p.description) " +
            "FROM DigitalProductDAO p ORDER BY p.price ASC")
    Optional<List<ProductBriefDTO>> getOrderedByPrice();

<<<<<<< Updated upstream
=======
    @Query("SELECT new com.gameshub.dto.product.ProductBriefDTO(p.id, p.price, p.title, p.description) " +
            "FROM DigitalProductDAO p WHERE p.sellerId = :sellerId")
    List<ProductBriefDTO> getAllBySellerID(@Param("sellerId") int sellerId);

>>>>>>> Stashed changes
}