package com.alten.products.api.products;

import com.alten.products.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @PostMapping("/here")
    private ProductOutput createProduct(@RequestBody ProductInput input) {
        Product product = new Product(
                input.code(),
                input.name(),
                input.description(),
                input.image(),
                input.category(),
                input.price(),
                input.quantity(),
                input.internalReference(),
                input.shellId(),
                input.inventoryStatus(),
                input.rating()
        );

        String name = input.name();
        System.out.println("name : " + name);
        Logger logger = LoggerFactory.getLogger(Class.class);
        logger.info("name : " + name);

        //bdd

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
}

