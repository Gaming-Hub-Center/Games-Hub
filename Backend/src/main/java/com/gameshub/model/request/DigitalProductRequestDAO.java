package com.gameshub.model.request;

import com.gameshub.model.product.DigitalProductDAO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "digital_product_request")
public class DigitalProductRequestDAO extends ProductRequestDAO {
    @Column(name = "code")
    private String code;

    public DigitalProductRequestDAO(
            int id,
            LocalDate dateReceived,
            String status,
            String requestType,
            String title,
            int price,
            String description,
            LocalDate postDate,
            int count,
            int sellerId,
            String category,
            String code
    ) {
        super(id, dateReceived, status, requestType, title, price, description, postDate, count, sellerId, category);
        this.code = code;
    }

    public DigitalProductRequestDAO(){
        this.title = "";
        this.dateReceived = LocalDate.now();
        this.status = "";
        this.requestType = "";
        this.price = 0;
        this.description = "";
        this.postDate = LocalDate.now();
        this.count = 0;
        this.category = "";
        this.code = "";
    }
}
