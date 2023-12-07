package com.gameshub.model.product;

import jakarta.persistence.Column;

public class DigitalProductDAO extends ProductDAO {
    @Column(name = "code")
    private String code;
}