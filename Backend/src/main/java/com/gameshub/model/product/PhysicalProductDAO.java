package com.gameshub.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "physical_product")
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(int id, String title, int price, String description, LocalDate postDate, int count, int sellerId, String category) {
        super(id, title, price, description, postDate, count, sellerId, category);
    }

    public PhysicalProductDAO(){
        this.title = "";
        this.price = 0;
        this.description = "";
        this.postDate = LocalDate.now();
        this.count = 0;
        this.category = "";
    }

}
