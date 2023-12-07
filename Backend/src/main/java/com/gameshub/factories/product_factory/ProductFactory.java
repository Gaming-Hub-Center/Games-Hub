package com.gameshub.factories.product_factory;

import com.gameshub.model.product.*;
import jakarta.transaction.NotSupportedException;

import java.time.LocalDate;

public class ProductFactory implements IProductFactory {

    public ProductDAO createProduct(ProductType type, int productId, String title, float price, String description,
                                    String physicalOrDigital, LocalDate postDate, int count, String code) throws NotSupportedException {
        if (type == ProductType.PHYSICAL) {
            PhysicalProductDAO physicalProduct = new PhysicalProductDAO();
            physicalProduct.setId(productId);
            physicalProduct.setTitle(title);
            physicalProduct.setPrice(price);
            physicalProduct.setDescription(description);
            physicalProduct.setPhysicalOrDigital(physicalOrDigital);
            physicalProduct.setPostDate(postDate);
            physicalProduct.setCount(count);
            return physicalProduct;
        } else if (type == ProductType.DIGITAL) {
            DigitalProductDAO digitalProduct = new DigitalProductDAO();
            digitalProduct.setId(productId);
            digitalProduct.setTitle(title);
            digitalProduct.setPrice(price);
            digitalProduct.setDescription(description);
            digitalProduct.setPhysicalOrDigital(physicalOrDigital);
            digitalProduct.setPostDate(postDate);
            digitalProduct.setCount(count);
            digitalProduct.setCode(code);
            return digitalProduct;
        } else {
            throw new NotSupportedException("Invalid product type");
        }
    }

}
