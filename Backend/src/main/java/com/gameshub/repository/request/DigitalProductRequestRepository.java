package com.gameshub.repository.request;

import com.gameshub.model.request.DigitalProductRequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DigitalProductRequestRepository extends JpaRepository<DigitalProductRequestDAO, Integer> {
    List<DigitalProductRequestDAO> findBySellerId(int sellerId);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);
}
