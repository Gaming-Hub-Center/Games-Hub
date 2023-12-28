package com.gameshub.repository.request;

import com.gameshub.controller.DTO.request.*;
import com.gameshub.model.request.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public interface DigitalProductRequestRepository extends JpaRepository<DigitalProductRequestDAO, Integer> {

    boolean existsByDescriptionAndTitle(String description, String title);
    boolean existsByDescriptionAndTitleAndSellerIdAndStatus(String description, String title, int sellerId, String status);
    List<DigitalProductRequestDAO> findBySellerId(int sellerId);
    Optional<DigitalProductRequestDAO> findById(int id);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE DigitalProductRequestDAO p SET p.title = :#{#patch.title}, p.description = :#{#patch.description} WHERE p.id = :id")
    int updateById(@Param("id") int id, @Param("patch") ProductPatchDTO patch);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE digitalproductrequest ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

    List<DigitalProductRequestDAO> findByStatus(String status);

}
