package com.gameshub.repository.product;

import com.gameshub.model.product.DigitalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {
}
