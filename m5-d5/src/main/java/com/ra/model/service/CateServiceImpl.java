package com.ra.model.service;

import com.ra.model.entity.Category;
import com.ra.repository.CateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CateServiceImpl implements CateService {
    @Autowired
    CateRepository cateRepository;

    @Override
    public List<Category> findAll() {
        return cateRepository.findAll();
    }

    @Override
    public Category findById(Integer id) {
        Optional<Category> categoryOptional = cateRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    @Override
    public Category save(Category category) {
        return cateRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        cateRepository.deleteById(id);
    }
}
