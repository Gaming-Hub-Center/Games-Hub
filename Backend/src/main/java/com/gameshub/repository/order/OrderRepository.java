package com.gameshub.repository.order;

import com.gameshub.model.order.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, Integer> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE `Order` ALTER COLUMN orderID RESTART WITH  1", nativeQuery = true)
    void resetAutoIncrement();

}
