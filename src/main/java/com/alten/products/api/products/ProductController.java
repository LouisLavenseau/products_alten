package com.alten.products.api.products;

import com.alten.products.domain.Product;
import com.alten.products.domain.ProductRepository;
import com.alten.products.utils.ProductMapper;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("{id}")
    private ProductOutput getProduct(@PathVariable long id ) {
        Product product = productRepository.getReferenceById(id);
        return ProductMapper.toOutput(product);
    }

    @GetMapping
    private List<ProductOutput> listProducts() {
        List<Product> products = productRepository.findAllByOrderByNameAsc();
        return ProductMapper.toOutputsList(products);
    }

    @PostMapping
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

        productRepository.save(product);

        return ProductMapper.toOutput(product);
    }

    @PatchMapping("{id}")
    private ProductOutput patchProduct(@PathVariable long id, @RequestBody ProductPatch patch) {
        Product product = productRepository.getReferenceById(id);

        product.patch(patch);

        productRepository.save(product);

        return ProductMapper.toOutput(product);
    }

    @DeleteMapping("{id}")
    private void deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}

