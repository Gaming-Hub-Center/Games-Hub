package com.gameshub.model.wishlist;

import com.gameshub.model.product.DigitalProductDAO;
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
@Table(name = "digitalwishlist")
public class DigitalWishlistDAO extends WishlistDAO {

    public DigitalWishlistDAO(int buyerId, int productId) {
        this.id = new WishlistKey(buyerId, productId);
    }

    @ManyToOne
    @JoinColumn(name = "Productid", insertable = false, updatable = false)
    protected DigitalProductDAO product;

}
