package com.gameshub.model.request;

import com.gameshub.model.product.PhysicalProductDAO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "physical_product_request")
public class PhysicalProductRequestDAO extends ProductRequestDAO {

    public PhysicalProductRequestDAO(
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
            String category
    ) {
        super(id, dateReceived, status, requestType, title, price, description, postDate, count, sellerId, category);
    }

    public PhysicalProductDAO getProduct() {
        return new PhysicalProductDAO(
                this.id,
                this.title,
                this.price,
                this.description,
                this.postDate,
                this.count,
                this.sellerId,
                this.category
        );
    }

    public PhysicalProductRequestDAO(){
        this.title = "";
        this.dateReceived = LocalDate.now();
        this.status = "";
        this.requestType = "";
        this.price = 0;
        this.description = "";
        this.postDate = LocalDate.now();
        this.count = 0;
        this.category = "";
    }


}
