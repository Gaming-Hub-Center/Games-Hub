package com.gameshub.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "digitalproduct")
public class DigitalProductDAO extends ProductDAO {

    @Column(name = "Code")
    private String code;

    public DigitalProductDAO(String title, float price, String description, int sellerID, int count, String category, String code, LocalDate postDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.sellerID = sellerID;
        this.count = count;
        this.category = category;
        this.code = code;
        this.postDate = postDate;
    }

}
