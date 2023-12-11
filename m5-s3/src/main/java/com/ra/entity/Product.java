package com.ra.entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    private String productName;
    private double productPrice;
    @ManyToOne
    @JoinColumn(name = "category_Id", referencedColumnName = "categoryId")
    private Category category;

    public Product() {
    }

    public Product(int productId, String productName, double productPrice, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
