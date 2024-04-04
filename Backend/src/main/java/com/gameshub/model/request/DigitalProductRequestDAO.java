package com.gameshub.model.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "digitalproductrequest")
public class DigitalProductRequestDAO extends ProductRequestDAO {

    public DigitalProductRequestDAO(LocalDate dateReceived, String status, String requestType, String title, float price, String description, LocalDate postDate, int count, String category, int sellerId) {
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
