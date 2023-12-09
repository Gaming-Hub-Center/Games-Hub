package com.gameshub.model.request;

import com.gameshub.model.product.PhysicalProductDAO;
import com.gameshub.model.product.ProductDAO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "physical_product_request")
public class PhysicalProductRequestDAO extends ProductRequestDAO {

}
