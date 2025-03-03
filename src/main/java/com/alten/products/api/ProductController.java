package com.alten.products.api;

import com.alten.products.dto.ProductInputDto;
import com.alten.products.dto.ProductOutputDto;
import com.alten.products.dto.ProductPatchRequest;
import com.alten.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{id}")
    private ProductOutputDto getProduct(@PathVariable long id ) {
        return productService.getProduct(id);
    }

    @GetMapping
    private List<ProductOutputDto> listProducts() {
        return productService.listProducts();
    }

    @PostMapping
    private ProductOutputDto createProduct(@RequestBody ProductInputDto input) {
        return productService.createProduct(input);
    }

    @PatchMapping("{id}")
    private ProductOutputDto patchProduct(@PathVariable long id, @RequestBody ProductPatchRequest patch) {
        return productService.patchProduct(id, patch);
    }

    @DeleteMapping("{id}")
    private void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}

