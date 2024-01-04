package com.ra.service;

import com.ra.model.entity.M5Category;
import com.ra.util.exception.BadRequestException;
import com.ra.util.exception.NotFoundException;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<M5Category> findsAvailable();
    M5Category save(M5Category category) throws BadRequestException;
    List<M5Category> findAll();
    M5Category categoryById(Long categoryId);
    void deleteCategory(Long id) throws BadRequestException;
    M5Category findById(Long id) throws NotFoundException;
}
