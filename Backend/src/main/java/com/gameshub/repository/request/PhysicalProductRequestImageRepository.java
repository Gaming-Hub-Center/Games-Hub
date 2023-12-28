package com.gameshub.repository.request;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.image.DigitalProductRequestImage;
import com.gameshub.model.request.image.PhysicalProductRequestImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalProductRequestImageRepository extends JpaRepository<PhysicalProductRequestImage, Integer> {
    List<PhysicalProductRequestImage> findByPhysicalProductRequest_Id(int requestId);

}
