package com.gameshub.repository.product;

import com.gameshub.model.product.DigitalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {
    List<DigitalProductDAO> findBySellerId(int sellerId);
    boolean existsBySellerId(int sellerId);
    DigitalProductDAO findById(int id);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);

}
