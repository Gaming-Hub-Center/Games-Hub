package com.gameshub.model.wishlist;

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
@Table(name = "physicalwishlist")
public class PhysicalWishlistDAO extends WishlistDAO {

    public PhysicalWishlistDAO(int buyerId, int productId) {
        this.id = new WishlistKey(buyerId, productId);
    }

    @ManyToOne
    @JoinColumn(name = "Productid", insertable = false, updatable = false)
    protected PhysicalProductDAO product;

}
