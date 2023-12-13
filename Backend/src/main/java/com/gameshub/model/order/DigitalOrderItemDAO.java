package com.gameshub.model.order;

import com.gameshub.model.product.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Digitalorderitem")
public class DigitalOrderItemDAO {

    public DigitalOrderItemDAO(DigitalOrderItemId digitalOrderItemId, int count, float unitPrice, float totalPrice) {
        this.id = digitalOrderItemId;
        this.count = count;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class DigitalOrderItemId implements Serializable {

        @Column(name = "orderID")
        private int orderID;

        @ManyToOne
        @JoinColumn(name = "productID", referencedColumnName = "ID")
        private DigitalProductDAO digitalProductDAO;

    }

    @EmbeddedId
    private DigitalOrderItemId id;

    @Column(name = "Count")
    private int count;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "total_price")
    private float totalPrice;

}