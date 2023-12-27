package com.gameshub.model.request;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Data
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
    protected float price;

    @Column(name = "Description")
    protected String description;

    @Column(name = "Postdate")
    protected LocalDate postDate;

    @Column(name = "Count")
    protected int count;

    @Column(name = "Category")
    protected String category;

    @ManyToOne
    @JoinColumn(name = "Sellerid")
    protected SellerDAO seller;

}
