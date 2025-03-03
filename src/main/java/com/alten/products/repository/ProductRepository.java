package com.alten.products.repository;

import com.alten.products.domain.Product;
import com.alten.products.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAllByOrderByNameAsc();
}
