package com.gameshub.model.request;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.ProductDAO;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class DigitalProductRequestDAO extends ProductRequestDAO{ // TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    @Column(name = "product_id")
    private DigitalProductDAO digitalProductDAO;

    @Override
    public void setProduct(ProductDAO digitalProductDAO) {
        this.digitalProductDAO = (DigitalProductDAO) digitalProductDAO;
    }

    @Override
    public ProductDAO getProduct() {
        return this.digitalProductDAO;
    }
}
