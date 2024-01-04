package com.ra.model.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    private String address;
    private String phone;
    private String note;
    private Date create_at;
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "UID")
    private User users;

    public Orders() {
    }

    public Orders(Integer order_id, String address, String phone, String note, Date create_at, Integer status, User users) {
        this.order_id = order_id;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.create_at = create_at;
        this.status = status;
        this.users = users;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public void setUsers(User users) {
        this.users = users;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUsers() {
        return users;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }



}
