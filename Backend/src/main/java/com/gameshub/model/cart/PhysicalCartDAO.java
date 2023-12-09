package com.gameshub.model.cart;


import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "`physical_cart`")
@NoArgsConstructor
public class PhysicalCartDAO extends CartDAO {

    @ManyToOne
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    protected PhysicalProductDAO product;

    public PhysicalCartDAO(CartKey id, int count, BuyerDAO buyer, PhysicalProductDAO product) {
        this.id = id;
        this.count = count;
        this.buyer = buyer;
        this.product = product;
    }


}
