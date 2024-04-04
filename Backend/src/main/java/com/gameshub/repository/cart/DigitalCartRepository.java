package com.gameshub.repository.cart;

import com.gameshub.model.cart.DigitalCartDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalCartRepository extends JpaRepository<DigitalCartDAO, DigitalCartDAO.CartKey> {
    List<DigitalCartDAO> findById_BuyerId(int buyerId);
    void deleteById(DigitalCartDAO.CartKey cartKey);
    void deleteById_BuyerId(int buyerId);
}