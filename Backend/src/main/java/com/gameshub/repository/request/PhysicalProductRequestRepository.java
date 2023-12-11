package com.gameshub.repository.request;

import com.gameshub.model.request.PhysicalProductRequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PhysicalProductRequestRepository extends JpaRepository<PhysicalProductRequestDAO, Integer> {
    List<PhysicalProductRequestDAO> findBySellerId(int sellerId);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);
}
