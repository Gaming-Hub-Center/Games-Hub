package com.gameshub.repository.request;

import com.gameshub.model.request.DigitalProductRequestDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DigitalProductRequestRepository extends JpaRepository<DigitalProductRequestDAO, Integer> {
    boolean existsByDescriptionAndTitle(String description, String title);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE digital_product_request ALTER COLUMN id RESTART WITH 1", nativeQuery = true)
    void resetAutoIncrement();

}
