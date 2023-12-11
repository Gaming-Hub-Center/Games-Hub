package com.gameshub.model.request;

import com.gameshub.model.user.SellerDAO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "digital_product_request")
public class DigitalProductRequestDAO extends ProductRequestDAO {
    @Column(name = "code")
    private String code;

    public DigitalProductRequestDAO(int id, LocalDate date, String status, String requestType, String title, int price, String description, LocalDate postDate, int count, String category, SellerDAO seller, String code) {
        super(id, date, status, requestType, title, price, description, postDate, count, category, seller);
        this.code = code;
    }
}
