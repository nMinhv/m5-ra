package com.ra.model.service;

import com.ra.dto.request.OrderRequestDTO;
import com.ra.dto.response.OrdersResponseDTO;
import com.ra.model.entity.Orders;
import com.ra.model.entity.User;

import java.util.List;

public interface OrderService {
    List<Orders> findAll();
    OrdersResponseDTO save(OrderRequestDTO requestOrder, User user);
}
