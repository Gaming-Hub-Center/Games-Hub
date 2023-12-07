package com.gameshub.model.product;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Blob;
import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

//    @Column(name = "title")
//    private String title;

    @Column(name = "price")
    private float price;

    @Lob
    @Column(name = "image")
    private Blob image;

    @Column(name = "description")
    private String description;

    @Column(name = "sellerID")
    private int sellerID;

//    @Column(name = "physicalordigital")
//    private String physicalOrDigital;
//
//    @Column(name = "postdate")
//    private LocalDate postDate;
//
//    @Column(name = "count")
//    private int count;
}