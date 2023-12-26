package com.gameshub.model.wishlist;

import com.gameshub.model.user.BuyerDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
public abstract class WishlistDAO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class WishlistKey {
        protected int buyerID;
        protected int productID;
    }

    @EmbeddedId
    protected WishlistKey id;

    @ManyToOne
    @JoinColumn(name = "Buyerid", insertable = false, updatable = false)
    protected BuyerDAO buyer;
}
