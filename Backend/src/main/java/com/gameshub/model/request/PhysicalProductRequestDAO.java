package com.gameshub.model.request;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "physicalproductrequest")
public class PhysicalProductRequestDAO extends ProductRequestDAO {

    public PhysicalProductRequestDAO(LocalDate dateReceived, String status, String requestType, String title, int price, String description, LocalDate postDate, int count, String category, SellerDAO seller) {
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
    }

}
