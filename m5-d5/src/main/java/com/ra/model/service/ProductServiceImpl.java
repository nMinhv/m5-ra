package com.ra.model.service;

import com.ra.model.entity.Product;
import com.ra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> productPage(Double min, Double max, String keyword, String sort, Pageable pageable) {
        if (sort != null) {
            if (sort.equals("desc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("productPrice").descending());
            } else if (sort.equals("asc")) {
                pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("productPrice").ascending());

            }
        }
        return productRepository.getAllByProductPriceBetweenAndProductNameContaining(min, max, keyword, pageable);
//        return productRepository.getAllByProductPriceBetween(min,max,pageable);
    }
}
