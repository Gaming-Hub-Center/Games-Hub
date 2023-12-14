package com.gameshub.model.order;

import com.gameshub.model.product.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "physicalorderitem")
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
        @Column(name = "Orderid")
        private int orderID;

        @ManyToOne
        @JoinColumn(name = "Productid")
        @OnDelete(action = OnDeleteAction.CASCADE)
        private PhysicalProductDAO physicalProductDAO;
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
