package com.gameshub.Repository;

import com.gameshub.Model.Users.DAOs.BuyerDAO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerDAO, Integer> {
    Optional<BuyerDAO> findByEmail(String email);
    Boolean existsByEmail(String email);
}