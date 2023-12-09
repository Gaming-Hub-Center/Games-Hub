package com.gameshub.repository;

import com.gameshub.model.product.DigitalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {
    Optional<DigitalProductDAO> findById(Integer ID);
}
