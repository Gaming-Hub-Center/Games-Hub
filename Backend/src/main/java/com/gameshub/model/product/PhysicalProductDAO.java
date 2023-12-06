package com.gameshub.model.product;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Builder
public class PhysicalProductDAO extends ProductDAO {

    public static abstract class PhysicalProductDAOBuilder extends ProductDAOBuilder<PhysicalProductDAOBuilder, PhysicalProductDAO> {
        @Override
        protected PhysicalProductDAO createProduct() {
            return new PhysicalProductDAO();
        }

        @Override
        protected PhysicalProductDAOBuilder self() {
            return this;
        }
    }
}
