package com.ra.model.entity;

import jakarta.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "order_id")
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "productId")
    private Product product;
    private Integer quantity;
    private Double price;
    public OrderDetail() {

    }

    public OrderDetail(Integer id, Orders order, Product product, Integer quantity, Double price) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
