package com.gameshub.model.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int productID;

    @Column(name = "price", nullable = false)
    protected float price;

    @Column(name = "count", nullable = false)
    protected int count;

    @Column(name = "name")
    protected String name;

    @Column(name = "Description", columnDefinition = "MEDIUMTEXT")
    protected String description;

    @Column(name = "category", nullable = false)
    protected String category;

    @Column(name = "SellerID", nullable = false)
    protected int sellerID;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    protected Date date;



    // Constructors, getters, and setters
}
