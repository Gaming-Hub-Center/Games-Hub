package com.gameshub.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "physicalproduct")
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(String title, float price, String description, int sellerId, int count, String category, LocalDate postDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.sellerId = sellerId;
        this.count = count;
        this.category = category;
        this.postDate = postDate;
    }

}
