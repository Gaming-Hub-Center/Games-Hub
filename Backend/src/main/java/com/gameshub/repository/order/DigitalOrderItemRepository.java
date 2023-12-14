package com.gameshub.repository.order;

import com.gameshub.model.order.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface DigitalOrderItemRepository extends JpaRepository<DigitalOrderItemDAO, DigitalOrderItemDAO.DigitalOrderItemId> { }
