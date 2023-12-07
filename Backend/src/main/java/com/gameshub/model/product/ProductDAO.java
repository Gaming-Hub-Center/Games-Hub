package com.gameshub.model.product;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Blob;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int productId;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "physicalordigital")
    private String physicalOrDigital;

    @Column(name = "postdate")
    private LocalDate postDate;

    @Column(name = "count")
    private int count;

    @Column(name = "sellerid")
    private int sellerId;

    @Lob
    @Column(name = "image")
    private Blob image;

}
