package com.gameshub.model.cart;

import com.gameshub.model.product.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "physicalcart")
public class PhysicalCartDAO extends CartDAO {

    public PhysicalCartDAO(CartKey id, int count) {
        this.id = id;
        this.count = count;
    }

    public PhysicalCartDAO(int buyerID, int productID, int count) {
        this.id = new CartKey(buyerID, productID);
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "Productid", insertable = false, updatable = false)
    protected PhysicalProductDAO product;

}
