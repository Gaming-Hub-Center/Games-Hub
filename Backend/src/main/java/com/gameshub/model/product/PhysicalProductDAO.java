package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "physical_product")
@NoArgsConstructor
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(int id, float price, String description, String title, int count, int sellerID, LocalDate created_date, String category) {
        setId(id);
        setPrice(price);
        setDescription(description);
        setTitle(title);
        setCount(count);
        setSellerID(sellerID);
        setCreated_date(created_date);
        setCategory(category);
    }
}