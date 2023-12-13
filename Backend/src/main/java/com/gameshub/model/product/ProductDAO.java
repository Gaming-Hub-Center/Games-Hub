package com.gameshub.model.product;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    protected int id;

    @Column(name = "title")
    protected String title;

    @Column(name = "price")
    protected int price;

    @Column(name = "description")
    protected String description;

    @Column(name = "post_date")
    protected LocalDate postDate; // Date Approved

    @Column(name = "count")
    protected int count;

    @Column(name = "sellerid")
    protected int sellerId;

    @Column(name = "category")
    protected String category;

}
