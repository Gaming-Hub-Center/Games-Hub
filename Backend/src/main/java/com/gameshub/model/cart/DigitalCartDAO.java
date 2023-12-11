package com.gameshub.model.cart;

import com.gameshub.model.product.DigitalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "digital_cart")
@NoArgsConstructor
public class DigitalCartDAO extends CartDAO {

    @ManyToOne
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    protected DigitalProductDAO product;

    public DigitalCartDAO(CartKey id, int count) {
        this.id = id;
        this.count = count;
        this.buyer = buyer;
        this.product = product;
    }



}
