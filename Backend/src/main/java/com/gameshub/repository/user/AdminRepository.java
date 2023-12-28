package com.gameshub.repository.user;

import com.gameshub.model.user.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

public interface AdminRepository extends JpaRepository<AdminDAO, Integer> {
    Optional<AdminDAO> findById(int id);
    Optional<AdminDAO> findByEmail(String email);
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE admin ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();
}
