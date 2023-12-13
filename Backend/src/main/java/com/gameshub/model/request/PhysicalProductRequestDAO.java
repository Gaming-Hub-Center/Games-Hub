package com.gameshub.model.request;

import com.gameshub.model.user.SellerDAO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "physical_product_request")
public class PhysicalProductRequestDAO extends ProductRequestDAO {

    public PhysicalProductRequestDAO(
            int id,
            LocalDate date,
            String status,
            String requestType,
            String title,
            int price,
            String description,
            LocalDate postDate,
            int count,
            String category,
            SellerDAO seller
    ) {
        super(id, date, status, requestType, title, price, description, postDate, count, category, seller);
    }

}
