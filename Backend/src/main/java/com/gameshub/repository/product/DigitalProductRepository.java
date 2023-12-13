package com.gameshub.repository.product;

import com.gameshub.model.product.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface DigitalProductRepository extends JpaRepository<DigitalProductDAO, Integer> {

}