package com.gameshub.repository.cart;

import com.gameshub.model.cart.PhysicalCartDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalCartRepository extends JpaRepository<PhysicalCartDAO, PhysicalCartDAO.CartKey> {
    List<PhysicalCartDAO> findById_BuyerId(int buyerId);
    void deleteById(PhysicalCartDAO.CartKey cartKey);
    void deleteById_BuyerId(int buyerId);
}