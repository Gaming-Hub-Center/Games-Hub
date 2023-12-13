package com.gameshub.model.cart;

import com.gameshub.model.user.BuyerDAO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Data
@MappedSuperclass
public abstract class CartDAO {

    @Data
    @Embeddable
    public static class CartKey implements Serializable {
        @Column(name = "buyerID")
        protected int buyerId;

        @Column(name = "productID")
        protected int productID;

        // Default constructor for JPA
        protected CartKey() {}

        public CartKey(int buyerID, int productID) {
            this.buyerID = buyerID;
            this.productID = productID;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CartKey)) return false;
            CartKey cartKey = (CartKey) o;
            return buyerID == cartKey.buyerID && productID == cartKey.productID;
        }

        @Override
        public int hashCode() {
            return Objects.hash(buyerID, productID);
        }
    }

    @EmbeddedId
    protected CartKey id;

    @Column(name = "Count", nullable = false)
    protected int count;

    @ManyToOne
    @JoinColumn(name = "buyerID", insertable = false, updatable = false)
    protected BuyerDAO buyer;



}
