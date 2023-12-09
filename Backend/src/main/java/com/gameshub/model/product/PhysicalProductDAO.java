package com.gameshub.model.product;

import com.gameshub.model.user.SellerDAO;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "physical_product")
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(int id, String title, int price, String description, LocalDate postDate, int count, SellerDAO sellerDAO, String category) {
        super(id, title, price, description, postDate, count, sellerDAO, category);
    }

}
