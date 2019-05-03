package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Order;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.OrderRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/order/get_orders_by_user_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestBody Map<String, Integer> user) {
        if (!user.containsKey("user_id"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderRepository.findOrdersByUser(user.get("user_id")), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/order/get_orders_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<List<Order>> getOrdersByUsername(@RequestBody Map<String, String> user) {
        if (!user.containsKey("username"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        int id = userRepository.findUserByUsername(user.get("username")).getId();
        return new ResponseEntity<>(orderRepository.findOrdersByUser(id), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/order/add_new_order_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<String> addNewOrderByUsername(@RequestBody Map<String, String> parameter) {
        if (!parameter.containsKey("username"))
            return new ResponseEntity<>("Missing parameter username", HttpStatus.BAD_REQUEST);

        Order newOrder = new Order();
        User user = userRepository.findUserByUsername(parameter.get("username"));
        LocalDate createDate = LocalDate.now();
        newOrder.setDate(createDate);
        newOrder.setUser(user);
        orderRepository.save(newOrder);
        return new ResponseEntity<>("Add new Order Successfully", HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/order/delete_order_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<String> deleteOrderByUsername(@RequestBody Map<String, String> user) {
        if (!user.containsKey("username"))
            return new ResponseEntity<>("Missing parameter username", HttpStatus.BAD_REQUEST);

        User orderUser = userRepository.findUserByUsername(user.get("username"));
        List<Order> orders = orderRepository.findOrdersByUser(orderUser.getId());
        for (Order order : orders) {
            orderRepository.delete(order);
        }
        return new ResponseEntity<>("Delete Order Successfully", HttpStatus.OK);
    }

}
