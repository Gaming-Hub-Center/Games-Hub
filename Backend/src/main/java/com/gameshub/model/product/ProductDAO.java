package com.gameshub.model.product;

import com.gameshub.model.user.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.*;

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
    protected float price;

    @Column(name = "description")
    protected String description;

    @Column(name = "postdate")
    protected LocalDate postDate; // Date Approved

    @Column(name = "count")
    protected int count;

    @ManyToOne
    @JoinColumn(name = "sellerid", referencedColumnName = "ID")
    protected SellerDAO sellerDAO;

}
