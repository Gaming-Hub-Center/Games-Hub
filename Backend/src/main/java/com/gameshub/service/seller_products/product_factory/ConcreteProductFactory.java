package com.gameshub.service.seller_products.product_factory;

import com.gameshub.model.product.DigitalProduct;
import com.gameshub.model.product.PhysicalProduct;
import com.gameshub.model.product.Product;
import com.gameshub.model.product.ProductType;

import java.util.Objects;

public class ConcreteProductFactory implements ProductFactory {

    // TODO Update after the constructor is completed
    @Override
    public Product createProduct(ProductType type) {
        if (Objects.requireNonNull(type) == ProductType.DIGITAL) {
            return new DigitalProduct();
        } else if (type == ProductType.PHYSICAL) {
            return new PhysicalProduct();
        }
        throw new IllegalArgumentException("Unknown product type");
    }
}
