package com.gameshub.repository.cart;

import com.gameshub.model.cart.*;
import com.gameshub.model.user.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface PhysicalCartRepository extends JpaRepository<PhysicalCartDAO, PhysicalCartDAO.CartKey> {
    List<PhysicalCartDAO> findByBuyerId(int buyerId);
    void deleteById(PhysicalCartDAO.CartKey cartKey);
    void deleteByBuyer(BuyerDAO buyer);
}