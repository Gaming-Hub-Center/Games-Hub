package com.gameshub.model.product;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // This annotation marks this class as a database entity
public class ProductRequest {

    @Id // This marks the field as a primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // This will auto-generate the primary key
    private Long requestId;

    @Column(nullable = false)
    private LocalDate dateReceived;

    @Column(nullable = false)
    private String status;

     @ManyToOne
     @JoinColumn(name = "product_id", nullable = false)
     private Product product;

}
