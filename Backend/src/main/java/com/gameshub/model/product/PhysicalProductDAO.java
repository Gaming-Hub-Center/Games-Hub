package com.gameshub.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "physicalproduct")
@NoArgsConstructor
public class PhysicalProductDAO extends ProductDAO {

    // Constructor
    public PhysicalProductDAO(int productID, float price, int count, String name, String description, String category, int sellerID, Date date) {
        this.productID = productID;
        this.price = price;
        this.count = count;
        this.name = name;
        this.description = description;
        this.category = category;
        this.sellerID = sellerID;
        this.date = date;
    }

    public PhysicalProductDAO(String title, float price, String description, LocalDate postDate, int count, int sellerID) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.postDate = postDate;
        this.count = count;
        this.sellerDAO = new SellerDAO();
        this.sellerDAO.setId(sellerID);
    }


    // Getters and setters
}
