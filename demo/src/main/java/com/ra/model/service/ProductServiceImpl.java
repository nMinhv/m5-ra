package com.ra.model.service;

import com.ra.model.entity.Product;
import com.ra.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product saveOrUpdate(Product product) {
        return productRepository.saveOrUpdate(product);
    }

    @Override
    public Product findById(Integer integer) {
        return productRepository.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        productRepository.delete(integer);

    }

    @Override
    public Boolean create(Product product) {
        return productRepository.create(product);
    }
}
