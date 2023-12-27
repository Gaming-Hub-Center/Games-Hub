package com.gameshub.repository.order;

import com.gameshub.model.order.*;
import com.gameshub.model.user.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, Integer> {
    List<OrderDAO> findByBuyerId(int buyerId);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE `order` ALTER COLUMN id RESTART WITH  1", nativeQuery = true)
    void resetAutoIncrement();
}
