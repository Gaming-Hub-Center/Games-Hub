package com.gameshub.model.request;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.product.ProductDAO;
import jakarta.persistence.*;

@Entity
@Table(name = "digital_product_request")
public class DigitalProductRequestDAO extends ProductRequestDAO { // TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "digital_product_id", referencedColumnName = "id", nullable = false)
    private DigitalProductDAO digitalProductDAO;

    @Override
    public void setProduct(ProductDAO digitalProductDAO) {
        this.digitalProductDAO = (DigitalProductDAO) digitalProductDAO;
    }

    @Override
    public DigitalProductDAO getProduct() {
        return this.digitalProductDAO;
    }
}
