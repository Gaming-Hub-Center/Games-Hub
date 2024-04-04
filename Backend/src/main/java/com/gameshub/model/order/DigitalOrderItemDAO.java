package com.gameshub.model.order;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "digitalorderitem")
public class DigitalOrderItemDAO {

    public DigitalOrderItemDAO(int orderId, int productId, int count, float unitPrice, float totalPrice) {
        this.id = new DigitalOrderItemId(orderId, productId);
        this.count = count;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class DigitalOrderItemId implements Serializable {
        @Column(name = "Orderid")
        protected int orderId;

        @Column(name = "Productid")
        protected int productId;
    }

    @EmbeddedId
    private DigitalOrderItemId id;

    @Column(name = "Count")
    private int count;

    @Column(name = "Unitprice")
    private float unitPrice;

    @Column(name = "Totalprice")
    private float totalPrice;

    @Transient
    List<String> codes;

}