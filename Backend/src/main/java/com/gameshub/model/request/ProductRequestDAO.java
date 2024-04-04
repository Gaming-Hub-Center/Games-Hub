package com.gameshub.model.request;

import jakarta.persistence.*;
<<<<<<< Updated upstream
import lombok.*;
import java.time.*;
=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> Stashed changes

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class ProductRequestDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    protected int id;

    @Column(name = "Datereceived")
    protected LocalDate dateReceived;

    @Column(name = "Status")
    protected String status;

    @Column(name = "Requesttype")
    protected String requestType;

    @Column(name = "Title")
    protected String title;

    @Column(name = "Price")
    protected int price;

    @Column(name = "Description")
    protected String description;

    @Column(name = "Postdate")
    protected LocalDate postDate;

    @Column(name = "Count")
    protected int count;

    @Column(name = "Category")
    protected String category;

    @Column(name = "Sellerid")
    protected int sellerId;

}
