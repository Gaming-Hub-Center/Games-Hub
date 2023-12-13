package com.gameshub.model.product;

import lombok.*;
import jakarta.persistence.*;

import java.time.*;

@Data
@MappedSuperclass
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    protected int id;

    @Column(name = "Title")
    protected String title;

    @Column(name = "Price")
    protected float price;

    @Column(name = "Description")
    protected String description;

    @Column(name = "Sellerid")
    protected int sellerID;

    @Column(name = "Count")
    protected int count;

    @Column(name = "Category")
    protected String category;

    @Column(name = "Postdate")
    protected LocalDate postDate;

}