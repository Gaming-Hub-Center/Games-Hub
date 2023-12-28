package com.gameshub.model.order;

import com.gameshub.model.product.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Digitalcode")
public class DigitalCode {

    public DigitalCode(int orderId, int productId, String code) {
        this.id = new DigitalCodeId();
        this.id.setOrderId(orderId);
        this.id.setProductId(productId);
        this.id.setCode(code);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class DigitalCodeId implements Serializable {
        @Column(name = "Orderid")
        private int orderId;

        @Column(name = "Productid")
        private int productId;

        @Column(name = "Code")
        private String code;
    }

    @EmbeddedId
    private DigitalCodeId id;

}
