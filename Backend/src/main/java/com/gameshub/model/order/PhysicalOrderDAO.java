package com.gameshub.model.order;

import com.gameshub.model.product.*;
import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Physicalorder")
public class PhysicalOrderDAO {

    @Data
    @Embeddable
    public static class PhysicalOrderId implements Serializable {

        @Column(name = "orderID")
        private int orderID;

        @ManyToOne
        @JoinColumn(name = "productID", referencedColumnName = "ID")
        private PhysicalProductDAO physicalProductDAO;

    }

    @EmbeddedId
    private PhysicalOrderId id;

//    @ManyToOne
//    @JoinColumn(name = "orderID", insertable = false, updatable = false)
//    private OrderDAO orderDAO;

//    @ManyToOne
//    @JoinColumn(name = "productID", referencedColumnName = "ID")
//    private PhysicalProductDAO physicalProductDAO;

    @Column(name = "Count")
    private int count;

    @Column(name = "unit_price")
    private float unitPrice;

    @Column(name = "total_price")
    private float totalPrice;

}
