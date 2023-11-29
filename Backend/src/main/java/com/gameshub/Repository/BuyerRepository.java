package com.gameshub.Repository;

import com.gameshub.Model.Users.BuyerDAO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerDAO, Integer> {
    BuyerDAO findByEmail(String email);
}
