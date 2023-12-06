package com.gameshub.service.seller_products.product_factory;

import com.gameshub.model.product.Product;
import com.gameshub.model.product.ProductType;
import org.springframework.stereotype.Component;

@Component
public interface ProductFactory {
    Product createProduct(ProductType type);
}
