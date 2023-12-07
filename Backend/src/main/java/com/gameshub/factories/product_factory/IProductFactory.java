package com.gameshub.factories.product_factory;

import com.gameshub.model.product.ProductDAO;
import com.gameshub.model.product.ProductType;
import jakarta.transaction.NotSupportedException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface IProductFactory {

    ProductDAO createProduct(ProductType type, int productId, String title, float price, String description,
                             String physicalOrDigital, LocalDate postDate, int count, String code) throws NotSupportedException;

}
