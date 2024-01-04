package com.ra.model.service;

import com.ra.dto.request.OrderRequestDTO;
import com.ra.dto.response.OrdersResponseDTO;
import com.ra.model.entity.Orders;
import com.ra.model.entity.User;
import com.ra.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailService orderDetailService) {
        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;

    }

    @Override
    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrdersResponseDTO save(OrderRequestDTO requestOrder, User user) {
        Orders orders = new Orders();
        orders.setUsers(user);
        orders.setAddress(requestOrder.getAddress());
        orders.setPhone(requestOrder.getPhone());
        orders.setNote(requestOrder.getNote());
        orders.setStatus(0);
        long t = new Date().getTime();
        java.sql.Date date = new java.sql.Date(t);
        orders.setCreate_at(date);
        return responseDTO(orderRepository.save(orders));
    }

    private OrdersResponseDTO responseDTO(Orders orders) {
        OrdersResponseDTO responseDTO = new OrdersResponseDTO();
        responseDTO.setOrderId(orders.getOrder_id());
        responseDTO.setUserName(orders.getUsers().getFullName());
        responseDTO.setList(orderDetailService.findAllByOrderId(orders.getOrder_id()));
        return responseDTO;
    }
}
