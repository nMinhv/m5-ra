package com.ra.model.service;

import com.ra.model.entity.OrderDetail;
import com.ra.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public List<OrderDetail> findAllByOrderId(Integer orderId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (OrderDetail o : orderDetailRepository.findAll()
        ) {
            if (o.getOrder().getOrder_id().equals(orderId)) {
                orderDetails.add(o);
            }
        }
        return orderDetails;
    }
}
