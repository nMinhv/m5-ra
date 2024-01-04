package com.ra.dto.response;

import com.ra.model.entity.OrderDetail;

import java.util.List;

public class OrdersResponseDTO {
    private Integer orderId;
    private String userName;
    private List<OrderDetail> list;

    public OrdersResponseDTO() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<OrderDetail> getList() {
        return list;
    }

    public void setList(List<OrderDetail> list) {
        this.list = list;
    }

    public OrdersResponseDTO(Integer orderId, String userName, List<OrderDetail> list) {
        this.orderId = orderId;
        this.userName = userName;
        this.list = list;
    }
}
