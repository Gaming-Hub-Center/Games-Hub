package com.gameshub.model.request;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "digitalproductrequestimagesurl")
public class DigitalProductRequestImage {

    public DigitalProductRequestImage(String imageUrl, int requestId) {
        this.imageUrl = imageUrl;
        this.requestId = requestId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Image")
    private String imageUrl;

    @Column(name = "Requestid")
    private int requestId;

}
