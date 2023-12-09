package com.gameshub.model.request;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "digital_product_request")
public class DigitalProductRequestDAO extends ProductRequestDAO {
    @Column(name = "code")
    private String code;
}
