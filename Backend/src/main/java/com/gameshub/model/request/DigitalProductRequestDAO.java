package com.gameshub.model.request;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
@Data
@NoArgsConstructor
@Entity
@Table(name = "digitalproductrequest")
public class DigitalProductRequestDAO extends ProductRequestDAO {

    @Column(name = "Code")
    private String code;

    public DigitalProductRequestDAO(LocalDate dateReceived, String status, String requestType, String title, float price, String description, LocalDate postDate, int count, String category, SellerDAO seller, String code) {
        this.dateReceived = dateReceived;
        this.status = status;
        this.requestType = requestType;
        this.title = title;
        this.price = price;
        this.description = description;
        this.postDate = postDate;
        this.count = count;
        this.category = category;
        this.seller = seller;
        this.code = code;
    }

}
