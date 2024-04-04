package com.gameshub.repository.order;

<<<<<<< Updated upstream
import com.gameshub.model.order.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

=======
import com.gameshub.model.order.OrderDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

>>>>>>> Stashed changes
@Repository
public interface OrderRepository extends JpaRepository<OrderDAO, Integer> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE `order` ALTER COLUMN id RESTART WITH  1", nativeQuery = true)
    void resetAutoIncrement();

}
