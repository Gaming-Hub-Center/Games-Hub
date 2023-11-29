package com.gameshub.Repository;

import com.gameshub.Model.Users.SellerDAO;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<SellerDAO, Integer> {
    Optional<SellerDAO> findByEmail(String email);
}
