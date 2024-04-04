package com.gameshub.repository.order;

import com.gameshub.model.order.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, Integer> {
    List<OrderDAO> findByBuyerId(int buyerId);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE `order` ALTER COLUMN id RESTART WITH  1", nativeQuery = true)
    void resetAutoIncrement();
}
