package com.ra.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UID;
    private String fullName;
    @Column(unique = true)
    private String email;

    public Integer getUID() {
        return UID;
    }

    public void setUID(Integer UID) {
        this.UID = UID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "users")
    Set<Orders> orders;

    public User() {
    }

    public User(Integer UID, String fullName, String email, Set<Orders> orders) {
        this.UID = UID;
        this.fullName = fullName;
        this.email = email;
        this.orders = orders;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
