package com.gameshub.model.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< Updated upstream
import java.time.*;
=======
import java.time.LocalDate;
>>>>>>> Stashed changes

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "digitalproductrequest")
public class DigitalProductRequestDAO extends ProductRequestDAO {

<<<<<<< Updated upstream
    @Column(name = "Code")
    private String code;

    public DigitalProductRequestDAO(LocalDate dateReceived, String status, String requestType, String title, int price, String description, LocalDate postDate, int count, String category, SellerDAO seller, String code) {
=======
    public DigitalProductRequestDAO(LocalDate dateReceived, String status, String requestType, String title, float price, String description, LocalDate postDate, int count, String category, int sellerId) {
>>>>>>> Stashed changes
        this.dateReceived = dateReceived;
        this.status = status;
        this.requestType = requestType;
        this.title = title;
        this.price = price;
        this.description = description;
        this.postDate = postDate;
        this.count = count;
        this.category = category;
        this.sellerId = sellerId;
    }

}
