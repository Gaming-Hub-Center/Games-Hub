package com.gameshub.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< Updated upstream:Backend/src/main/java/com/gameshub/controller/DTO/request/ProductRequestDTO.java
import java.time.*;
=======
import java.time.LocalDate;
import java.util.List;
>>>>>>> Stashed changes:Backend/src/main/java/com/gameshub/dto/product/ProductRequestDTO.java

@Getter
@Setter
@NoArgsConstructor
public class ProductRequestDTO {
    private LocalDate dateReceived;
    private String status;
    private String requestType;
    private String title;
    private int price;
    private String description;
    private LocalDate postDate;
    private int count;
    private int sellerId;
    private String category;
}
