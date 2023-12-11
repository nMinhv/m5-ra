package com.ra.service;

import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductService productService;

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existed(Integer id) {
        List<Product> list = productService.findAll();
        for (Product product : list) {
            if (product.getCategory().getCategoryId() == id) {
                return true;
            }
        }
        return false;
    }
}
