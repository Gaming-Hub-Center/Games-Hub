package com.gameshub.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "physical_product")
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(int id, String title, int price, String description, LocalDate postDate, int count, int sellerId) {
        super(id, title, price, description, postDate, count, sellerId);
    }

}
