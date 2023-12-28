package com.gameshub.repository.order;

import com.gameshub.model.order.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface DigitalCodeRepository extends JpaRepository<DigitalCode, Integer> {
    List<DigitalCode> findById_OrderIdAndId_ProductId(int orderId, int productId);
}
