package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "digital_product")
@NoArgsConstructor
public class DigitalProductDAO extends ProductDAO {
    @Column(name = "code")
    private String code;
}