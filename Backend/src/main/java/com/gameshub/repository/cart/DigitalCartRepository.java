package com.gameshub.repository.cart;

import com.gameshub.model.cart.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface DigitalCartRepository extends JpaRepository<DigitalCartDAO, DigitalCartDAO.CartKey> {
    List<DigitalCartDAO> findByBuyerId(int buyerId);
    void deleteById(DigitalCartDAO.CartKey cartKey);
}