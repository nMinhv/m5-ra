package com.ra.repository;

import com.ra.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Page<Product> getAllByProductPriceBetweenAndProductNameContaining(Double min, Double max, String keyword,Pageable pageable);
    Page<Product> getAllByProductPriceBetween(Double min, Double max, Pageable pageable);
}
