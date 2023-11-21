package com.gameshub.Repository;

import com.gameshub.Model.Users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    Buyer findByEmail(String email);
}
