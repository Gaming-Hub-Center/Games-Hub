package com.gameshub.repository.order;

import com.gameshub.model.order.DigitalOrderItemDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalOrderItemRepository extends JpaRepository<DigitalOrderItemDAO, DigitalOrderItemDAO.DigitalOrderItemId> { }
