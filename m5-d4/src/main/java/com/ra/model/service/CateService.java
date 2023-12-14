package com.ra.model.service;

import com.ra.model.entity.Category;

import java.util.List;

public interface CateService {
    List<Category> findAll();
    Category findById(Integer id);
    Category save(Category category);
    void delete(Integer id);
}
