package com.gameshub.repository.request;

import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalProductRequestRepository extends JpaRepository<PhysicalProductRequestDAO, Integer> {
//    PhysicalProductRequestDAO getPhysicalProductRequestDAOBySellerAndTitle(SellerDAO seller, String title);
}
