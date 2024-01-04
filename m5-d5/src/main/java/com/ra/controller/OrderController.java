package com.ra.controller;

import com.ra.dto.request.OrderRequestDTO;
import com.ra.dto.response.OrdersResponseDTO;
import com.ra.model.entity.Orders;
import com.ra.model.entity.User;
import com.ra.model.service.OrderService;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final UserService userService;
    private final OrderService orderService;
    @Autowired
    public OrderController(UserService userService , OrderService orderService){
    this.orderService = orderService;
    this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<Orders>> orderList(){
        List<Orders> orders = orderService.findAll();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<OrdersResponseDTO> saveOrder(@RequestBody OrderRequestDTO orderRequest){
        User user = userService.findUser(orderRequest.getUserId());
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OrdersResponseDTO responseDTO =  orderService.save(orderRequest,user);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
