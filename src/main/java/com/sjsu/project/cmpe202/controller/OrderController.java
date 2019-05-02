package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Item;
import com.sjsu.project.cmpe202.model.Order;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.ItemRepository;
import com.sjsu.project.cmpe202.repository.OrderRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Map.Entry;

@RestController
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(
            value = "/order/get_orders_by_user_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestBody Map<String, Integer> user) {
        if (!user.containsKey("user_id"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderRepository.findOrdersByUser(user.get("user_id")), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/order/get_orders_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<List<Order>> getOrdersByUsername(@RequestBody Map<String, String> user) {
        if (!user.containsKey("username"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        int id = userRepository.findByUsername(user.get("username")).getId();
        return new ResponseEntity<>(orderRepository.findOrdersByUser(id), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/order/add_new_order_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<String> addNewOrderByUsername(@RequestBody Map<String, String> parameter) {
        if (!parameter.containsKey("username"))
            return new ResponseEntity<>("Missing parameter username", HttpStatus.BAD_REQUEST);
        Order newOrder = new Order();
        User user = userRepository.findByUsername(parameter.get("username"));
        Set<Item> items = new HashSet<>();
        Double amount = 0.0;
        for (Map.Entry<String, String> entry : parameter.entrySet()) {
            if (entry.getKey().contains("item")) {
                Item item = itemRepository.findItemByProduct(entry.getValue());
                amount += item.getPrice();
                items.add(item);
            }
        }
        LocalDate createDate = LocalDate.now();

        newOrder.setAmount(amount);
        newOrder.setDate(createDate);
        newOrder.setItems(items);
        newOrder.setUser(user);
        orderRepository.save(newOrder);
        return new ResponseEntity<>("Add new Order Successfully", HttpStatus.OK);
    }
}
