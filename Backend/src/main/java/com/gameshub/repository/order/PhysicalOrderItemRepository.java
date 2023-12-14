package com.gameshub.repository.order;

import com.gameshub.model.order.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface PhysicalOrderItemRepository extends JpaRepository<PhysicalOrderItemDAO, PhysicalOrderItemDAO.PhysicalOrderItemId> { }
