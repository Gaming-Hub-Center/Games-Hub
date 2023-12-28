package com.gameshub.model.wishlist;

import com.gameshub.model.product.PhysicalProductDAO;
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
@Table(name = "physicalwishlist")
public class PhysicalWishlistDAO extends WishlistDAO {

    public PhysicalWishlistDAO(WishlistKey id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "Productid", insertable = false, updatable = false)
    protected PhysicalProductDAO product;
}
