package com.gameshub.repository.request;

import com.gameshub.controller.DTO.ProductPatchDTO;
import com.gameshub.model.request.DigitalProductRequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DigitalProductRequestRepository extends JpaRepository<DigitalProductRequestDAO, Integer> {
    List<DigitalProductRequestDAO> findBySellerId(int sellerId);
    Optional<DigitalProductRequestDAO> findById(int id);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);

    @Transactional
    @Modifying
    @Query("UPDATE DigitalProductRequestDAO p SET p.title = :#{#patch.title}, p.description = :#{#patch.description} WHERE p.id = :id")
    int updateById(@Param("id") int id, @Param("patch") ProductPatchDTO patch);
}
