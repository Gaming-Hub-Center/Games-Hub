package com.gameshub.repository.product;

import com.gameshub.model.product.PhysicalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProductDAO, Integer> {
    List<PhysicalProductDAO> findBySellerId(int sellerId);
    boolean existsBySellerId(int sellerId);
    PhysicalProductDAO findById(int id);

    @Transactional
    long deleteByIdAndSellerId(int id, int sellerId);
}
