package com.gameshub.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "digital_product")
@NoArgsConstructor
public class DigitalProductDAO extends ProductDAO {
    @Column(name = "code")
    private String code;

    public DigitalProductDAO(
            int productID, 
            float price, 
            int count, 
            String title, 
            String description, 
            String category,  
            int sellerID, 
            Date created_date, 
            String code
    ) {
        super(productID, price, description, title, count, sellerID, created_date, category);
        this.code = code;
    }
}
