package com.gameshub.repository.request;

import com.gameshub.model.request.PhysicalProductRequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalProductRequestRepository extends JpaRepository<PhysicalProductRequestDAO, Integer> {
}
