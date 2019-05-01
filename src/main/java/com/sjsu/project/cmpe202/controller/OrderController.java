package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Order;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.OrderRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(
            value = "/order/get_orders_by_user_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Order> getOrdersByUserId(@RequestBody Map<String, Integer> userId) {
        return orderRepository.findOrdersByUser(userId.get("user_id"));
    }

    @RequestMapping(
            value = "/order/get_orders_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Order> getOrdersByUsername(@RequestBody Map<String, String> username) {
        User user = userRepository.findByUsername(username.get("username"));
        return orderRepository.findOrdersByUser(user.getId());
    }
}
