package com.alten.products.service;

import com.alten.products.dto.ProductInputDto;
import com.alten.products.dto.ProductOutputDto;
import com.alten.products.dto.ProductPatchRequest;
import com.alten.products.domain.Product;
import com.alten.products.exception.NotFoundException;
import com.alten.products.repository.ProductRepository;
import com.alten.products.utils.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductOutputDto getProduct(long id) {
        Product product = findByIdSafe(id);
        return ProductMapper.toOutput(product);
    }

    public List<ProductOutputDto> listProducts() {
        List<Product> products = productRepository.findAllByOrderByNameAsc();
        return ProductMapper.toOutputsList(products);
    }

    public ProductOutputDto createProduct(ProductInputDto input) {
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

    public ProductOutputDto patchProduct(long id, ProductPatchRequest patch) {
        Product product = findByIdSafe(id);

        product.patch(patch);

        productRepository.save(product);

        return ProductMapper.toOutput(product);
    }

    public void deleteProduct(long id) {
        Product product = findByIdSafe(id);
        productRepository.delete(product);
    }

    private Product findByIdSafe(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with ID " + id + " not found"));
    }
}
