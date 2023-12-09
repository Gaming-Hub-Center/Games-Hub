package com.gameshub.model.cart;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`digital_cart`")
@NoArgsConstructor
public class DigitalCartDAO extends CartDAO {

    @ManyToOne
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    protected DigitalProductDAO product;

    public DigitalCartDAO(CartKey id, int count, BuyerDAO buyer, DigitalProductDAO product) {
        this.id = id;
        this.count = count;
        this.buyer = buyer;
        this.product = product;
    }



}
