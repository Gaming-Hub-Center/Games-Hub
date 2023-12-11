package com.gameshub.model.cart;


import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.user.BuyerDAO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "physical_cart")
@NoArgsConstructor
public class PhysicalCartDAO extends CartDAO {

    @ManyToOne
    @JoinColumn(name = "productID", insertable = false, updatable = false)
    protected PhysicalProductDAO product;

    public PhysicalCartDAO(CartKey id, int count) {
        this.id = id;
        this.count = count;
    }


}
