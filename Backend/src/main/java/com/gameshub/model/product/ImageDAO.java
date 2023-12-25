package com.gameshub.model.product;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Blob;

@Data
@MappedSuperclass
public class ImageDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "image")
    private byte[] image;
}
