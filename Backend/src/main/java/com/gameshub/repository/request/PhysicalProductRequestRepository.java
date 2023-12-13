package com.gameshub.repository.request;

import com.gameshub.model.request.PhysicalProductRequestDAO;
import com.gameshub.model.user.SellerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhysicalProductRequestRepository extends JpaRepository<PhysicalProductRequestDAO, Integer> {
    boolean existsByDescriptionAndTitle(String description, String title);

    boolean existsByDescriptionAndTitleAndSellerIdAndStatus(String description, String title, int sellerId, String status);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE physicalproductrequest ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
