package com.gameshub.model.ProductImage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gameshub.model.product.PhysicalProductDAO;
import jakarta.persistence.FetchType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "physical_product_image")
public class PhysicalProductProductImage extends ProductImage {

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "physical_product_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PhysicalProductDAO physicalProductDAO;

}
