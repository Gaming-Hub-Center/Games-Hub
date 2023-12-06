package com.gameshub.model.product;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Data
@Entity // This annotation marks this class as a database entity
public class ProductRequestDAO {

    @Id // This marks the field as a primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // This will auto-generate the primary key
    private Long requestId;

    @Column(nullable = false)
    private LocalDate dateReceived;

    @Column(nullable = false)
    private String status;

    //TODO fix issue
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false)
//    private Product product; // Reference to the Product entity using product_id as foreign key

}
