package com.gameshub.repository.order;

import com.gameshub.model.order.PhysicalOrderItemDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalOrderItemRepository extends JpaRepository<PhysicalOrderItemDAO, PhysicalOrderItemDAO.PhysicalOrderItemId> { }
