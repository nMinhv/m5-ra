package com.ra.controller;

import com.ra.model.entity.Product;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

//    @GetMapping("")
//    public ResponseEntity<List<Product>> productIndex() {
//        List<Product> list = productService.findAll();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
    @GetMapping("")
    public ResponseEntity<?> pageProduct(@RequestParam(defaultValue = "0",name = "page")int page,
                                         @RequestParam(defaultValue = "3",name = "size")int size,
                                         @RequestParam(defaultValue = "",name = "search")String search,
                                         @RequestParam(required = false,name = "sort")String sort,
                                         @RequestParam(defaultValue = "0",name = "min")Double min,
                                         @RequestParam(defaultValue = "9999999",name = "max")Double max){
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> productPage = productService.productPage(min,max,search,sort,pageable);
        return new ResponseEntity<>(productPage,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> productById(@PathVariable("id") Integer id) {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product newPro = productService.save(product);
        if (newPro == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newPro, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,
                                          @PathVariable("id") Integer id) {

        if (productService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product updateProduct = productService.save(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") Integer id) {
        if (productService.findById(id) != null) {
            productService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
