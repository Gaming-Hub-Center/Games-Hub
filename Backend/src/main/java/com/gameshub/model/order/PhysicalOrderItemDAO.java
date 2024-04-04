package com.gameshub.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "physicalorderitem")
public class PhysicalOrderItemDAO {

    public PhysicalOrderItemDAO(int orderId, int productId, int count, float unitPrice, float totalPrice) {
        this.id = new PhysicalOrderItemId(orderId, productId);
        this.count = count;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class PhysicalOrderItemId implements Serializable {
        @Column(name = "Orderid")
        protected int orderId;

        @Column(name = "Productid")
        protected int productId;
    }

    @EmbeddedId
    private PhysicalOrderItemId id;

    @Column(name = "Count")
    private int count;

    @Column(name = "Unitprice")
    private float unitPrice;

    @Column(name = "Totalprice")
    private float totalPrice;

}
