package com.gameshub.repository.product;

import com.gameshub.dto.product.ProductPatchDTO;
import com.gameshub.model.request.PhysicalProductRequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhysicalProductRequestRepository extends JpaRepository<PhysicalProductRequestDAO, Integer> {

    boolean existsByDescriptionAndTitle(String description, String title);
    boolean existsByDescriptionAndTitleAndSellerIdAndStatus(String description, String title, int sellerId, String status);
    List<PhysicalProductRequestDAO> findBySellerId(int sellerId);
    Optional<PhysicalProductRequestDAO> findById(int id);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE PhysicalProductRequestDAO p SET p.title = :#{#patch.title}, p.description = :#{#patch.description} WHERE p.id = :id")
    int updateById(@Param("id") int id, @Param("patch") ProductPatchDTO patch);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE physicalproductrequest ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    List<PhysicalProductRequestDAO> findByStatus(String status);

}
