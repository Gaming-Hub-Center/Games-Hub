package com.gameshub.model.cart;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@MappedSuperclass
public abstract class CartDAO {

    @Data
    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class CartKey implements Serializable {
        @Column(name = "Buyerid")
        protected int buyerID;

        @Column(name = "Productid")
        protected int productID;
    }

    @EmbeddedId
    protected CartKey id;

    @Column(name = "Count", nullable = false)
    protected int count;

    @ManyToOne
    @JoinColumn(name = "Buyerid", insertable = false, updatable = false)
    protected BuyerDAO buyer;

}

