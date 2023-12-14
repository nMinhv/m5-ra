package com.ra.controller;
import com.ra.entity.Category;
import com.ra.entity.Product;
import com.ra.service.CategoryService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("")

    public String home(Model model) {
        model.addAttribute("list", categoryService.findAll());
        return "home";
    }

    @GetMapping("/category")
    public String cate(Model model) {
        model.addAttribute("list", categoryService.findAll());
        return "cate";
    }

    @GetMapping("/add-category")
    public String addCate(Model model) {
        Category category = new Category();
        model.addAttribute("cate", category);
        return "addCate";
    }

    @GetMapping("/edit-category/{id}")
    public String editCate(Model model, @PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        model.addAttribute("cate", category);
        return "editCate";
    }

    @PostMapping("/save-category")
    public String saveCate(@ModelAttribute("cate") Category category) {
        if (categoryService.saveOrUpdate(category) != null) {
            return "redirect:/category";
        }
        return "redirect:/add-category";
    }

    @GetMapping("/delete-category/{id}")
    public String deleteCate(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        if (!categoryService.existed(id)) {
            categoryService.delete(id);
        } else {
            attributes.addFlashAttribute(" ", "trong danh muc van con sp nen ko the xoa");
        }
        return "redirect:/category";
    }

    // product
    @GetMapping("/product")
    public String product(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @GetMapping("/add-product")
    public String addProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list);
        return "addPro";
    }

    @GetMapping("edit-product/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        List<Category> list = categoryService.findAll();
        model.addAttribute("list", list);
        return "editPro";
    }

    @PostMapping("/save-product")
    public String saveProduct(@ModelAttribute("product") Product product) {
        if (productService.saveOrUpdate(product) != null) {
            return "redirect:/product";
        }
        return "redirect:/add-product";
    }
    @GetMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.delete(id);
        return "redirect:/product";
    }
}
