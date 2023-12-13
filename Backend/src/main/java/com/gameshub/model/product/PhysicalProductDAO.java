package com.gameshub.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "physical_product")
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(
            int id,
            float price,
            String description,
            String title,
            int count,
            int sellerID,
            LocalDate created_date,
            String category
    ) {
        super(id, price, description, title, count, sellerID, created_date, category);
    }
}