package com.gameshub.repository.product;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.user.SellerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {
}