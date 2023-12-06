package com.gameshub.model.product;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "digital_product")
public final class DigitalProductDAO extends ProductDAO {

    @Column(name = "code")
    private String code;

    public static abstract class DigitalProductDAOBuilder extends ProductDAOBuilder<DigitalProductDAOBuilder, DigitalProductDAO> {
        @Override
        protected DigitalProductDAO createProduct() {
            return new DigitalProductDAO();
        }

        public DigitalProductDAOBuilder productCode(String code) {
            product.setCode(code);
            return self();
        }

        @Override
        protected DigitalProductDAOBuilder self() {
            return this;
        }
    }
}
