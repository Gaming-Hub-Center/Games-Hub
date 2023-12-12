package com.gameshub.repository.user;

import com.gameshub.model.user.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerDAO, Integer> {
    Optional<BuyerDAO> findByEmail(String email);
    Boolean existsByEmail(String email);
}