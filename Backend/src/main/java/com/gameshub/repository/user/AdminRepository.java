package com.gameshub.repository.user;

import com.gameshub.model.user.AdminDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminDAO, Integer> { }
