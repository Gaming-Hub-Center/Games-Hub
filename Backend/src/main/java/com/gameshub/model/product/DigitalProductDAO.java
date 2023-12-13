package com.gameshub.model.product;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "digitalproduct")
public class DigitalProductDAO extends ProductDAO {

    public DigitalProductDAO(String title, float price, String description, LocalDate postDate, int count, int sellerID) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.postDate = postDate;
        this.count = count;
        this.sellerDAO = new SellerDAO();
        this.sellerDAO.setId(sellerID);
    }

}