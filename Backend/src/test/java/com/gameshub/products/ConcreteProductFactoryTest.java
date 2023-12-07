package com.gameshub.products;

import com.gameshub.factories.product_factory.ProductFactory;
import com.gameshub.model.product.*;
import jakarta.transaction.NotSupportedException;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ConcreteProductFactoryTest {

    @Test
    public void testCreatePhysicalProduct() {
        ProductFactory productFactory = new ProductFactory();

        ProductDAO physicalProduct = null;
        try {
            physicalProduct = productFactory.createProduct(ProductType.PHYSICAL,
                    1, "Physical Product", 50, "This is a physical product",
                    "Physical", LocalDate.now(), 10, null);
        } catch (NotSupportedException e) {
            fail("Exception not expected for Physical Product creation");
        }

        assertNotNull(physicalProduct);
        assertTrue(physicalProduct instanceof PhysicalProductDAO);
        assertEquals("Physical Product", physicalProduct.getTitle());
    }

    @Test
    public void testCreateDigitalProduct() {
        ProductFactory productFactory = new ProductFactory();

        ProductDAO digitalProduct = null;
        try {
            digitalProduct = productFactory.createProduct(ProductType.DIGITAL,
                    2, "Digital Product", 30, "This is a digital product",
                    "Digital", LocalDate.now(), 20, "ABC123");
        } catch (NotSupportedException e) {
            fail("Exception not expected for Digital Product creation");
        }

        assertNotNull(digitalProduct);
        assertTrue(digitalProduct instanceof DigitalProductDAO);
        assertEquals("Digital Product", digitalProduct.getTitle());
        assertEquals("ABC123", ((DigitalProductDAO) digitalProduct).getCode());
    }

    @Test
    public void testCreateInvalidProductType() {
        ProductFactory productFactory = new ProductFactory();

        try {
            productFactory.createProduct(null, 3, "Invalid Product", 20,
                    "This is an invalid product", "InvalidType", LocalDate.now(), 5, null);
            fail("Expected NotSupportedException was not thrown");
        } catch (NotSupportedException e) {
            assertEquals("Invalid product type", e.getMessage());
        }
    }
}
