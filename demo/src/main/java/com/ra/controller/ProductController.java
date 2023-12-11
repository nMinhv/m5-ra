package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @RequestMapping("product")
    public String allProduct (Model model){
        List<Product> list = productService.findAll();
        model.addAttribute("list", list);
        return "product";
    }
    @GetMapping("add-product")
    public String add (Model m){
        List<Category> listCategory = categoryService.findAll();
        Product product = new Product();
        m.addAttribute("product", product);
        m.addAttribute("listCategory", listCategory);
        return "add-product";
    }

    @PostMapping("create-product")
    public String create (@ModelAttribute("product") Product  product){
        productService.create(product);
        return "redirect:product";
    }
    @GetMapping("edit-product/{id}")
    public  String edit(@PathVariable("id") Integer id, Model m){
        Product product = productService.findById(id);
        m.addAttribute(product);
        List<Category> categoryList = categoryService.findAll();
        m.addAttribute("categoryList",categoryList);
        return "edit-product";
    }
    @PostMapping("update-product")
    public  String update(@ModelAttribute("product")Product product){
        productService.saveOrUpdate(product);
        return "redirect:product";
    }

    @GetMapping("delete-product/{id}")
    public  String delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product";
    }

}
