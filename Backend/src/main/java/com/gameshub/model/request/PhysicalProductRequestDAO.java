package com.gameshub.model.request;

import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class PhysicalProductRequestDAO extends ProductRequestDAO{ // TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @Column(name = "product_id")
    private PhysicalProductDAO physicalProductDAO;

    @Override
    public void setProduct(ProductDAO physicalProductDAO) {
        this.physicalProductDAO = (PhysicalProductDAO) physicalProductDAO;
    }

    @Override
    public ProductDAO getProduct() {
        return this.physicalProductDAO;
    }


}
