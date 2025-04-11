package com.aressa.oktaraserver.product.mapper;

import com.aressa.oktaraserver.product.dto.ProductDTO;
import com.aressa.oktaraserver.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .stock(product.getStock())
                .sku(product.getSku())
                .minStock(product.getMinStock())
                .location(product.getLocation())
                .unit(product.getUnit())
                .createdAt(product.getCreatedAt())
                .createdBy(product.getCreatedBy() != null ? product.getCreatedBy().getFullName(): null)
                .updatedAt(product.getUpdatedAt())
                .updatedBy(product.getUpdatedBy() != null ? product.getUpdatedBy().getFullName() : null)
                .build();
    }
}
