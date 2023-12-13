package com.gameshub.model.order;

import com.gameshub.model.product.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Physicalorderitem")
public class PhysicalOrderItemDAO {

    public PhysicalOrderItemDAO(PhysicalOrderItemId physicalOrderItemId, int count, float unitPrice, float totalPrice) {
        this.id = physicalOrderItemId;
        this.count = count;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class PhysicalOrderItemId implements Serializable {

        @Column(name = "orderID")
        private int orderID;

        @ManyToOne
        @JoinColumn(name = "productID", referencedColumnName = "ID")
        private PhysicalProductDAO physicalProductDAO;

    }

    @EmbeddedId
    private PhysicalOrderItemId id;

    @Column(name = "Count")
    private int count;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "total_price")
    private float totalPrice;

}
