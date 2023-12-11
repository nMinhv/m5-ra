package com.ra.model.entity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status")
    private boolean status;
    @Column (name = "name")
    private  String name;
    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
    }

    public Category(Integer id, boolean status, String name, Set<Product> products) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.products = products;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}