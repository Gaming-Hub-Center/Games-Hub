package com.gameshub.model.product;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;

    @Column(name = "count")
    private int count;

    @Column(name = "sellerID")
    private int sellerID;

    @Column(name = "created_date")
    private LocalDate created_date;

    @Column(name = "category")
    private String category;
}