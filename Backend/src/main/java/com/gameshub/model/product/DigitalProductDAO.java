package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "digitalproduct")
@Getter
@Setter
@NoArgsConstructor // No-argument constructor for JPA
public class DigitalProductDAO extends ProductDAO {

    @Column(name = "Code", nullable = false)
    private String code;

    // Constructor
    public DigitalProductDAO(int productID, float price, int count, String name, String description, String category, int sellerID, Date date, String code) {
        this.productID = productID;
        this.price = price;
        this.count = count;
        this.name = name;
        this.description = description;
        this.category = category;
        this.sellerID = sellerID;
        this.date = date;
        this.code = code;
    }


    // Getters and setters
}
