package com.gameshub.model.product;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Entity
@Table(name = "product") // Update table name as needed
@SuperBuilder
public abstract class ProductDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long productId;

    @Column(length = 50, name = "title")
    private String title;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "physicalordigital")
    private String physicalOrDigital;

    @Column(name = "postdate")
    private LocalDate postDate;

    @Column(name = "count")
    private Integer count;

    @Builder
    public abstract static class ProductDAOBuilder<B extends ProductDAOBuilder<B, P>, P extends ProductDAO> {
        protected P product;

        protected ProductDAOBuilder() {
            product = createProduct();
        }

        // Abstract method to be implemented by subclasses
        protected abstract P createProduct();

        // Builder methods
        public B productId(Long productId) {
            product.setProductId(productId);
            return self();
        }

        public B productTitle(String title) {
            product.setTitle(title);
            return self();
        }

        public B productPrice(Double price) {
            product.setPrice(price);
            return self();
        }

        public B productDescription(String description) {
            product.setDescription(description);
            return self();
        }

        public B physicalOrDigital(String physicalOrDigital) {
            product.setPhysicalOrDigital(physicalOrDigital);
            return self();
        }

        public B productPostDate(LocalDate localDate) {
            product.setPostDate(localDate);
            return self();
        }

        public B productCount(Integer count) {
            product.setCount(count);
            return self();
        }

        protected abstract B self();

        public P build() {
            return product;
        }
    }
}
