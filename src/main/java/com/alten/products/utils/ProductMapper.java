package com.alten.products.utils;

import com.alten.products.domain.Product;
import com.alten.products.dto.ProductOutputDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductOutputDto toOutput(Product product) {
        return new ProductOutputDto(
                product.getId(),
                product.getCode(),
                product.getName(),
                product.getDescription(),
                product.getImage(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity(),
                product.getInternalReference(),
                product.getShellId(),
                product.getInventoryStatus(),
                product.getRating(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }

    public static List<ProductOutputDto> toOutputsList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toOutput)
                .collect(Collectors.toList());
    }
}