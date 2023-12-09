package com.gameshub.repository;

import com.gameshub.model.product.DigitalImageDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DigitalImageRepository extends JpaRepository<DigitalImageDAO, Integer> {
    @Query("SELECT p.image FROM DigitalImageDAO p")
    Optional<List<byte[]>> findAllByProduct_id(int product_id);
}
