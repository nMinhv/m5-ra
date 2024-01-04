package com.ra.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private Double productPrice;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "categoryId")
    private Category category;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer product_id) {
        this.productId = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product_name) {
        this.productName = product_name;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double product_price) {
        this.productPrice = product_price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product(Integer product_id, String product_name, Double product_price, Category category) {
        this.productId = product_id;
        this.productName = product_name;
        this.productPrice = product_price;
        this.category = category;
    }

    public Product() {
    }
}
