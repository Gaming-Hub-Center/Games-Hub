package com.gameshub.model.cart;

import com.gameshub.model.product.PhysicalProductDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "physicalcart")
public class PhysicalCartDAO extends CartDAO {

    public PhysicalCartDAO(int buyerId, int productId, int count) {
        this.id = new CartKey(buyerId, productId);
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name = "Productid", insertable = false, updatable = false)
    protected PhysicalProductDAO product;

}
