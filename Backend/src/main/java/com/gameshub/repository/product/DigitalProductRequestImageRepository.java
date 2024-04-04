package com.gameshub.repository.product;

import com.gameshub.model.request.DigitalProductRequestImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DigitalProductRequestImageRepository extends JpaRepository<DigitalProductRequestImage, Integer> {
    List<DigitalProductRequestImage> findByRequestId(int requestId);
}
