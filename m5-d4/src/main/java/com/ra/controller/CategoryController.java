package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CateService cateService;

    @GetMapping("")
    public ResponseEntity<List<Category>> categoryIndex() {
        List<Category> list = cateService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable("id") Integer id) {
        Category category = cateService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category newCate = cateService.save(category);
        if (newCate == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newCate, HttpStatus.OK);
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category,
                                           @PathVariable("id") Integer id) {

        if (cateService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Category updateCate = cateService.save(category);
        return new ResponseEntity<>(updateCate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") Integer id) {
        if (cateService.findById(id) != null) {
            cateService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
