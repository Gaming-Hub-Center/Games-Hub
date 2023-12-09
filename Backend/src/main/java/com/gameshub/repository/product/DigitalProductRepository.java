package com.gameshub.repository.product;

import com.gameshub.model.product.DigitalProductDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {
}
