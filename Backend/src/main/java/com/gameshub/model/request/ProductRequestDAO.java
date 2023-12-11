package com.gameshub.model.request;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class ProductRequestDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "date_received")
    protected LocalDate dateReceived;

    @Column(name = "status")
    protected String status;

    @Column(name = "request_type")
    protected String requestType;

    @Column(name = "title")
    protected String title;

    @Column(name = "price")
    protected int price;

    @Column(name = "description")
    protected String description;

    @Column(name = "post_date")
    protected LocalDate postDate;

    @Column(name = "count")
    protected int count;

    @JoinColumn(name = "seller_id")
    protected int sellerId;

    @Column(name = "category")
    protected String category;

}
