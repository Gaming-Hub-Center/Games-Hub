package com.gameshub.model.product;

import javax.persistence.Column;

public class DigitalProduct extends Product {
    @Column(name = "code")
    private String code;
}
