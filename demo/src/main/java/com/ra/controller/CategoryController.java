package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("category")
    public  String demo(Model m){
        List<Category> list = categoryService.findAll();
        m.addAttribute("list", list);
        return "category";
    }
    @GetMapping("add")
    public String add(Model m ){
        Category category = new Category();
        m.addAttribute("category",category);
        return "add-category";
    }
    @PostMapping("create")
    public String create(@ModelAttribute("category")Category category ){
        categoryService.create(category);
        return "redirect:category";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable("id")Integer id, Model m){
        Category category = categoryService.findById(id);
        m.addAttribute("category",category);
        return "edit";
    }
    @PostMapping("update")
    public String update(@ModelAttribute("category") Category category){
        categoryService.saveOrUpdate(category);
        return "redirect:category";
    }
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer categoryId){
        categoryService.delete(categoryId);
        return "redirect:/category";
    }

}
