package com.gameshub.repository;

import com.gameshub.model.product.PhysicalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalProductRepository  extends JpaRepository<PhysicalProductDAO, Integer> {
}
