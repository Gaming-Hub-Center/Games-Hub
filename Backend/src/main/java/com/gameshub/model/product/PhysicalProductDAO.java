package com.gameshub.model.product;

import com.gameshub.model.user.SellerDAO;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Entity
@Table(name = "physicalproduct")
@NoArgsConstructor
public class PhysicalProductDAO extends ProductDAO {

    public PhysicalProductDAO(String title, float price, String description, SellerDAO seller, int count, String category, LocalDate postDate) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.seller = seller;
        this.count = count;
        this.category = category;
        this.postDate = postDate;
    }

}
