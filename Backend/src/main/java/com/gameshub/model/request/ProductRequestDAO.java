package com.gameshub.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    protected int price;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected SellerDAO seller;

}
