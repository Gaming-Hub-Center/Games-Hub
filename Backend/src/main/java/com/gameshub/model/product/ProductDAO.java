package com.gameshub.model.product;

import com.gameshub.model.user.SellerDAO;
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
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private float price;

    @Column(name = "description")
    private String description;

    @Column(name = "postdate")
    private LocalDate postDate; // Date Approved

    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "ID")
    private SellerDAO seller;

    @Column(name = "category")
    private String category;

}
