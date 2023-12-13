package com.gameshub.repository;

import com.gameshub.model.product.DigitalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {
    Optional<DigitalProductDAO> findById(int productId);
}
