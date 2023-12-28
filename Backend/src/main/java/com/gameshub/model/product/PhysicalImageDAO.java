package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "physical_product_image")
@NoArgsConstructor
public class PhysicalImageDAO extends ImageDAO {
    @Column(name = "physical_product_id")
    int product_id;

    public PhysicalImageDAO(int id, String url, int product_id) {
        setId(id);
        setUrl(url);
        this.product_id = product_id;
    }

    public PhysicalImageDAO(String url, int product_id) {
        setUrl(url);
        this.product_id = product_id;
    }
}
