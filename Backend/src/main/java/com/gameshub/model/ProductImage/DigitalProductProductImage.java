package com.gameshub.model.ProductImage;

import com.gameshub.model.product.DigitalProductDAO;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "digital_product_image")
public class DigitalProductProductImage extends ProductImage {

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "digital_product_id", referencedColumnName = "id")
    private DigitalProductDAO digitalProductDAO;

}
