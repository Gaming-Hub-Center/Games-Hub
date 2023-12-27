package com.gameshub.model.request.image;

import com.gameshub.model.request.PhysicalProductRequestDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "physicalproductrequestimagesurl")
public class PhysicalProductRequestImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // This is the missing identifier

    @Column(name = "Image")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestId", referencedColumnName = "Id")
    private PhysicalProductRequestDAO physicalProductRequest;

    public PhysicalProductRequestImage(String imageUrl, PhysicalProductRequestDAO physicalProductRequestDAO) {
        this.imageUrl = imageUrl;
        this.physicalProductRequest = physicalProductRequestDAO;
    }
}
