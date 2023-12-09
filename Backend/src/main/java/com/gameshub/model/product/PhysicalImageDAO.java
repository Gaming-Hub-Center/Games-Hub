package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "physical_product_image")
@NoArgsConstructor
public class PhysicalImageDAO extends ImageDAO {
    // TODO on deletion cascaded deletion
    @Column(name = "physical_product_id")
    int product_id;
}
