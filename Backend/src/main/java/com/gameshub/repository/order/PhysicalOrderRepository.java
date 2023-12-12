package com.gameshub.repository.order;

import com.gameshub.model.order.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PhysicalOrderRepository extends JpaRepository<PhysicalOrderDAO, PhysicalOrderDAO.PhysicalOrderId> {
}
