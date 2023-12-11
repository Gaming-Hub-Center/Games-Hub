package com.gameshub.model.request;

import com.gameshub.model.product.DigitalProductDAO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "digital_product_request")
public class DigitalProductRequestDAO extends ProductRequestDAO {
    @Column(name = "code")
    private String code;

    public DigitalProductDAO getProduct() {
        return new DigitalProductDAO(
                this.id,
                this.title,
                this.price,
                this.description,
                this.postDate,
                this.count,
                this.sellerId,
                this.code,
                this.category
        );
    }
}
