package com.ra.model.service;

import com.ra.model.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> findAll();
    List<OrderDetail> findAllByOrderId(Integer orderId);
}
