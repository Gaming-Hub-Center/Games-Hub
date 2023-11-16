package com.gameshub.Repository;

import com.gameshub.Model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Seller findByEmail(String email);
}
