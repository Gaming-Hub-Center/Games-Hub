package com.gameshub.repository.user;

import com.gameshub.model.user.BuyerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerDAO, Integer> {
    Optional<BuyerDAO> findById(int id);
    Optional<BuyerDAO> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE buyer ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}