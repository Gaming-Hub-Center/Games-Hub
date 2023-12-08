package com.gameshub.model.product;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "digital_product")
public final class DigitalProductDAO extends ProductDAO {

    @Column(name = "code")
    private String code;

    public DigitalProductDAO(int id, String title, int price, String description, LocalDate postDate, int count, int sellerId, String code, ArrayList<Integer> image_id) {
        super(id, title, price, description, postDate, count, sellerId);
        this.code = code;
    }

}
