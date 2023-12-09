package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "digital_product_image")
@NoArgsConstructor
public class DigitalImageDAO extends ImageDAO {
    // TODO on deletion cascaded deletion
    @Column(name = "digital_product_id")
    int product_id;
}
