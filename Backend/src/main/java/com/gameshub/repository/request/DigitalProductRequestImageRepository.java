package com.gameshub.repository.request;

import com.gameshub.model.request.DigitalProductRequestDAO;
import com.gameshub.model.request.image.DigitalProductRequestImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalProductRequestImageRepository extends JpaRepository<DigitalProductRequestImage, Integer> {
    List<DigitalProductRequestImage> findByDigitalProductRequest(DigitalProductRequestDAO digitalProductRequest);

}
