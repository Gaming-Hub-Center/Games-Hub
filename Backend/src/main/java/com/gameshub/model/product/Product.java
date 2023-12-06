package com.gameshub.model.product;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long productId;

    @Column(length = 50, name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "physicalordigital")
    private String physicalOrDigital;

    @Column(name = "postdate")
    private LocalDate postDate;

    @Column(name = "count")
    private Integer count;
}
