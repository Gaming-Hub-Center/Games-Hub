package com.gameshub.model.order;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "digitalcode")
public class DigitalCode {

    public DigitalCode(int orderId, int productId, String code) {
        this.id = new DigitalCodeId(orderId, productId, code);
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
