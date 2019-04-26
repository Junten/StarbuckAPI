package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Order;
import com.sjsu.project.cmpe202.model.Payment;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.OrderRepository;
import com.sjsu.project.cmpe202.repository.PaymentRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CardRepository cardRepository;

    @RequestMapping(
            value = "/get_payments_by_user_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByUserId(@RequestBody Map<String, Integer> userId) {
        return paymentRepository.findPaymentsByUser(userId.get("user_id"));
    }

    @RequestMapping(
            value = "/get_payments_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByUsername(@RequestBody Map<String, String> username) {
        User user = userRepository.findByUsername(username.get("username"));
        return paymentRepository.findPaymentsByUser(user.getId());
    }

    @RequestMapping(
            value = "/get_payments_by_card_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByCardId(@RequestBody Map<String, Integer> cardId) {
        return paymentRepository.findPaymentsByCard(cardId.get("card_id"));
    }

    @RequestMapping(
            value = "/get_payments_by_order_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByOrderId(@RequestBody Map<String, Integer> orderId) {
        return paymentRepository.findPaymentsByOrder(orderId.get("order_id"));
    }
}
