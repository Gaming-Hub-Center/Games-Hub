package com.gameshub.model.request.image;

import com.gameshub.model.request.DigitalProductRequestDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "digitalproductrequestimagesurl")
public class DigitalProductRequestImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "image")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestid", referencedColumnName = "Id")
    private DigitalProductRequestDAO digitalProductRequest;

    public DigitalProductRequestImage(String imageUrl, DigitalProductRequestDAO digitalProductRequestDAO) {
        this.digitalProductRequest = digitalProductRequestDAO;
        this.imageUrl = imageUrl;
    }
}
