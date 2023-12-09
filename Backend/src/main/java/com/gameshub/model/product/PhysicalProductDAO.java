package com.gameshub.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "PhysicalProduct")
@NoArgsConstructor
public class PhysicalProductDAO extends ProductDAO {

    // Constructor
    public PhysicalProductDAO(int productID, float price, int count, String name, String description, String category, int sellerID, Date date) {
        this.productID = productID;
        this.price = price;
        this.count = count;
        this.name = name;
        this.description = description;
        this.category = category;
        this.sellerID = sellerID;
        this.date = date;
    }


    // Getters and setters
}
