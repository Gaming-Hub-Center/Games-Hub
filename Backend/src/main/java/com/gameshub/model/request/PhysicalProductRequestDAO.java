package com.gameshub.model.request;

<<<<<<< Updated upstream
import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
>>>>>>> Stashed changes

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "physicalproductrequest")
public class PhysicalProductRequestDAO extends ProductRequestDAO {

<<<<<<< Updated upstream
    public PhysicalProductRequestDAO(LocalDate dateReceived, String status, String requestType, String title, int price, String description, LocalDate postDate, int count, String category, SellerDAO seller) {
=======
    public PhysicalProductRequestDAO(LocalDate dateReceived, String status, String requestType, String title, float price, String description, LocalDate postDate, int count, String category, int sellerId) {
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
