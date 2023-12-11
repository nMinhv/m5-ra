package com.ra.service;

import com.ra.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category saveOrUpdate(Category category);

    Category findById(Integer id);
    void  delete(Integer id);
    boolean existed(Integer id);
}
