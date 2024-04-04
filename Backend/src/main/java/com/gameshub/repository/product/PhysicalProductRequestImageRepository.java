package com.gameshub.repository.product;

import com.gameshub.model.request.PhysicalProductRequestImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalProductRequestImageRepository extends JpaRepository<PhysicalProductRequestImage, Integer> {
    List<PhysicalProductRequestImage> findByRequestId(int requestId);
}
