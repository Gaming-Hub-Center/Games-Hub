package com.gameshub.Repository;

import com.gameshub.Model.Users.SellerDAO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface SellerRepository extends JpaRepository<SellerDAO, Integer> {
    SellerDAO findByEmail(String email);
}
