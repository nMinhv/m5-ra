package com.ra.model.service;
import com.ra.model.entity.Product;
import java.util.List;
public interface ProductService {
    List<Product> findAll();
    Product findById(Integer id);
    Product save(Product category);
    void delete(Integer id);
}
