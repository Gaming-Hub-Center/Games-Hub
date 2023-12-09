package com.gameshub.repository.request;

import com.gameshub.model.request.DigitalProductRequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalProductRequestRepository extends JpaRepository<DigitalProductRequestDAO, Integer> {
}
