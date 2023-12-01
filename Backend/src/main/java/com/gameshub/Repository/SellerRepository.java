package com.gameshub.Repository;

import com.gameshub.Model.User.DAO.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface SellerRepository extends JpaRepository<SellerDAO, Integer> {
    Optional<SellerDAO> findByEmail(String email);
    Boolean existsByEmail(String email);
}
