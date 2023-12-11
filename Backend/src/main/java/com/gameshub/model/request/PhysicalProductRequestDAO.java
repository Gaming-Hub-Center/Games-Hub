package com.gameshub.model.request;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "physical_product_request")
public class PhysicalProductRequestDAO extends ProductRequestDAO {
    public PhysicalProductDAO getProduct() {
        return new PhysicalProductDAO(
                this.id,
                this.title,
                this.price,
                this.description,
                this.postDate,
                this.count,
                this.sellerId,
                this.category
        );
    }
}
