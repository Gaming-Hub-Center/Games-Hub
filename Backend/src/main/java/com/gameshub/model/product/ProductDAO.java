package com.gameshub.model.product;

<<<<<<< Updated upstream
import lombok.*;
import jakarta.persistence.*;
=======
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
>>>>>>> Stashed changes

import java.time.LocalDate;

@Getter
@Setter
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
    protected int sellerId;

    @Column(name = "Count")
    protected int count;

    @Column(name = "Category")
    protected String category;

    @Column(name = "Postdate")
    protected LocalDate postDate;

}
