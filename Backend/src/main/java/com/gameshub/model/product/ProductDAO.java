package com.gameshub.model.product;

import lombok.*;
import jakarta.persistence.*;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;

    @Column(name = "postdate")
    private LocalDate postDate; // Date Approved

    @Column(name = "count")
    private int count;

    @Column(name = "sellerid")
    private int sellerId;

    @Column(name = "category")
    private String category;

//    @Lob
//    @Column(name = "image_id")
//    private ArrayList<Integer> image;

}
