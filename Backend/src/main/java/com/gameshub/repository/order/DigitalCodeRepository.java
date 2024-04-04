package com.gameshub.repository.order;

import com.gameshub.model.order.DigitalCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DigitalCodeRepository extends JpaRepository<DigitalCode, Integer> {
    List<DigitalCode> findById_OrderIdAndId_ProductId(int orderId, int productId);
}
