package com.gameshub.model.wishlist;

import com.gameshub.model.product.DigitalProductDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "digitalwishlist")
public class DigitalWishlistDAO extends WishlistDAO {

    public DigitalWishlistDAO(WishlistKey id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "Productid", insertable = false, updatable = false)
    protected DigitalProductDAO product;
}
