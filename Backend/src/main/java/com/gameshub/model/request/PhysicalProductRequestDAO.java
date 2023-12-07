package com.gameshub.model.request;

import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import jakarta.persistence.*;

@Entity
@Table(name = "physical_product_request")
public class PhysicalProductRequestDAO extends ProductRequestDAO { // TODO

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @Column(name = "product_id")
    private PhysicalProductDAO physicalProductDAO;

    @Override
    public void setProduct(ProductDAO physicalProductDAO) {
        this.physicalProductDAO = (PhysicalProductDAO) physicalProductDAO;
    }

    @Override
    public PhysicalProductDAO getProduct() {
        return this.physicalProductDAO;
    }

}
