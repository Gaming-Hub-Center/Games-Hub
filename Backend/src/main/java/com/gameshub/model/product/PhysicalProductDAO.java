package com.gameshub.model.product;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "physicalproduct")
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(int id, String title, int price, String description, LocalDate postDate, int count, SellerDAO sellerDAO) {
        super(id, title, price, description, postDate, count, sellerDAO);
    }

}