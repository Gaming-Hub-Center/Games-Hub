package com.gameshub.repository.user;

import com.gameshub.model.user.SellerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<SellerDAO, Integer> {
    Optional<SellerDAO> findById (int id);
    Optional<SellerDAO> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE seller ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}