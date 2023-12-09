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
    private int id;

    @Column(name = "date_received")
    private LocalDate dateReceived;

    @Column(name = "status")
    private String status;

    @Column(name = "request_type")
    private String requestType;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "post_date")
    private LocalDate postDate;

    @Column(name = "count")
    private int count;

    @JoinColumn(name = "seller_id")
    private int sellerId;

    @Column(name = "category")
    private String category;

}
