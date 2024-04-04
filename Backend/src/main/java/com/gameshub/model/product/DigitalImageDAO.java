package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "digital_product_image")
public class DigitalImageDAO extends ImageDAO {

    @Column(name = "digital_product_id")
    int productId;

    public DigitalImageDAO(String url, int productId) {
        this.url = url;
        this.productId = productId;
    }

}
