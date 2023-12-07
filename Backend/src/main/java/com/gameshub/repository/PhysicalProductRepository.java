package com.gameshub.repository;

import com.gameshub.model.product.PhysicalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PhysicalProductRepository extends JpaRepository<PhysicalProductDAO, Integer> {
    Optional<PhysicalProductDAO> findById(Integer ID);
}
