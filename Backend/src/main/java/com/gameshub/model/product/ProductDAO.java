package com.gameshub.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gameshub.model.user.SellerDAO;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(name = "Count")
    protected int count;

    @Column(name = "Category")
    protected String category;

    @Column(name = "Postdate")
    protected LocalDate postDate;

    @ManyToOne
    @JoinColumn(name = "Sellerid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected SellerDAO seller;

}