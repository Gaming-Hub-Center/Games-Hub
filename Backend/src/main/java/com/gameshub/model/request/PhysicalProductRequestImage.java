package com.gameshub.model.request;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "physicalproductrequestimagesurl")
public class PhysicalProductRequestImage {

    public PhysicalProductRequestImage(String imageUrl, int requestId) {
        this.imageUrl = imageUrl;
        this.requestId = requestId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Image")
    private String imageUrl;

    @Column(name = "Requestid")
    private int requestId;

}
