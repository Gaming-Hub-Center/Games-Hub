package com.gameshub.model.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class CartDAO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class CartKey implements Serializable {
        @Column(name = "Buyerid")
        protected int buyerId;

        @Column(name = "Productid")
        protected int productId;
    }

    @EmbeddedId
    protected CartKey id;

    @Column(name = "Count", nullable = false)
    protected int count;

}
