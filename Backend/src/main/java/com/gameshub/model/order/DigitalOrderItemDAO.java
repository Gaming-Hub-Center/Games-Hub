package com.gameshub.model.order;

import com.gameshub.model.product.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "digitalorderitem")
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
        @Column(name = "Orderid")
        private int orderId;

        @ManyToOne
        @JoinColumn(name = "Productid")
        private DigitalProductDAO digitalProductDAO;
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