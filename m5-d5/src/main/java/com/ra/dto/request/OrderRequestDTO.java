package com.ra.dto.request;

import java.util.List;

public class OrderRequestDTO {
    private Integer userId;
    private String address;
    private String phone;
    private String note;

    private List<OrderDetailDTO> list;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(Integer userId, String address, String phone, String note, List<OrderDetailDTO> list) {
        this.userId = userId;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.list = list;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public List<OrderDetailDTO> getList() {
        return list;
    }

    public void setList(List<OrderDetailDTO> list) {
        this.list = list;
    }
}
