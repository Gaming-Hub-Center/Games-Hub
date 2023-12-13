package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "digital_product")
public final class DigitalProductDAO extends ProductDAO {

    @Column(name = "code")
    private String code;

    public DigitalProductDAO(int id, String title, int price, String description, LocalDate postDate, int count, int sellerId, String code, String category) {
        super(id, title, price, description, postDate, count, sellerId, category);
        this.code = code;
    }

    public DigitalProductDAO(){
        this.title = "";
        this.price = 0;
        this.description = "";
        this.postDate = LocalDate.now();
        this.count = 0;
        this.category = "";
        this.code = "";
    }

}
