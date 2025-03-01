package com.alten.products.utils;

import com.alten.products.api.products.ProductOutput;
import com.alten.products.domain.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductOutput toOutput(Product product) {
        return new ProductOutput(
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

    public static List<ProductOutput> toOutputsList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toOutput)
                .collect(Collectors.toList());
    }
}