package com.ra.model.service;

import com.ra.model.entity.Category;
import com.ra.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Boolean create(Category category) {
        return categoryRepository.create(category);
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.saveOrUpdate(category);
    }

    @Override
    public Category findById(Integer integer) {
        return categoryRepository.findById(integer);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.delete(id);
    }
}
