package com.ra.model.service;

import com.ra.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
    Product save(Product category);
    void delete(Integer id);
    Page<Product> productPage(Double min,Double max,String keyword,String sort, Pageable pageable);
}
