package com.gameshub.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name = "physical_product")
@NoArgsConstructor
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(String title, float price, String description, int sellerID, int count, String category, LocalDate postDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.sellerID = sellerID;
        this.count = count;
        this.category = category;
        this.postDate = postDate;
    }

}