package com.gameshub.model.wishlist;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class WishlistDAO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class WishlistKey implements Serializable {
        @Column(name = "Buyerid")
        protected int buyerId;

        @Column(name = "Productid")
        protected int productId;
    }

    @EmbeddedId
    protected WishlistKey id;

}
