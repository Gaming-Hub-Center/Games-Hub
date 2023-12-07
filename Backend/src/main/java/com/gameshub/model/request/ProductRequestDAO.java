package com.gameshub.model.request;

import com.gameshub.model.product.ProductDAO;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "product_request")
public abstract class ProductRequestDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private int requestId;

    @Column(name = "date_received")
    private LocalDate dateReceived;

    @Column(name = "status")
    private String status;

    public abstract void setProduct(ProductDAO product); // Abstract method to be implemented by child classes

    public abstract ProductDAO getProduct();
}
