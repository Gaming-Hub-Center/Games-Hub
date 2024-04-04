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
@Table(name = "physical_product_image")
public class PhysicalImageDAO extends ImageDAO {

    @Column(name = "physical_product_id")
    int productId;

<<<<<<< Updated upstream
    public PhysicalImageDAO(int id, byte[] image, int product_id) {
        setId(id);
        setImage(image);
        this.product_id = product_id;
    }
=======
    public PhysicalImageDAO(String url, int productId) {
        this.url = url;
        this.productId = productId;
    }

>>>>>>> Stashed changes
}
