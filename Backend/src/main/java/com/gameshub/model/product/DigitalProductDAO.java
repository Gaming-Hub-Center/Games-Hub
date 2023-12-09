package com.gameshub.model.product;

import com.gameshub.model.user.SellerDAO;
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

    public DigitalProductDAO(int id, String title, int price, String description, LocalDate postDate, int count, SellerDAO sellerDAO, String code, String category) {
        super(id, title, price, description, postDate, count, sellerDAO, category);
        this.code = code;
    }

}
