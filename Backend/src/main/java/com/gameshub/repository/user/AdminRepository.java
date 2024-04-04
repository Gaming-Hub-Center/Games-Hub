package com.gameshub.repository.user;

import com.gameshub.model.user.AdminDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminDAO, Integer> {
    Optional<AdminDAO> findById(int id);
    Optional<AdminDAO> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE admin ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}