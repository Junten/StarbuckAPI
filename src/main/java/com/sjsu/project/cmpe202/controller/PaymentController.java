package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Card;
import com.sjsu.project.cmpe202.model.Order;
import com.sjsu.project.cmpe202.model.Payment;
import com.sjsu.project.cmpe202.model.User;
import com.sjsu.project.cmpe202.repository.CardRepository;
import com.sjsu.project.cmpe202.repository.OrderRepository;
import com.sjsu.project.cmpe202.repository.PaymentRepository;
import com.sjsu.project.cmpe202.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            value = "/payment/get_payments_by_user_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByUserId(@RequestBody Map<String, Integer> userId) {
        return paymentRepository.findPaymentsByUser(userId.get("user_id"));
    }

    @RequestMapping(
            value = "/payment/get_payments_by_username",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByUsername(@RequestBody Map<String, String> username) {
        User user = userRepository.findByUsername(username.get("username"));
        return paymentRepository.findPaymentsByUser(user.getId());
    }

    @RequestMapping(
            value = "/payment/get_payments_by_card_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByCardId(@RequestBody Map<String, Integer> cardId) {
        return paymentRepository.findPaymentsByCard(cardId.get("card_id"));
    }

    @RequestMapping(
            value = "/payment/get_payments_by_card_number",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByCardNumber(@RequestBody Map<String, String> cardNumber) {
        int cardId = cardRepository.findCardByCardNumber(cardNumber.get("card_number")).getId();
        return paymentRepository.findPaymentsByCard(cardId);
    }

    @RequestMapping(
            value = "/payment/get_payments_by_order_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public List<Payment> getPaymentsByOrderId(@RequestBody Map<String, Integer> orderId) {
        return paymentRepository.findPaymentsByOrder(orderId.get("order_id"));
    }

    @RequestMapping(
            value = "/payment/get_payment_by_payment_id",
            method = RequestMethod.POST,
            consumes = "application/json")
    public Payment getPaymentByPaymentId(@RequestBody Map<String, Integer> payment) {
        return paymentRepository.findPaymentById(payment.get("payment_id"));
    }

    @RequestMapping(
            value = "/payment/add_new_payment",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<String> addNewPayment(@RequestBody Map<String, String> parameter) {
        String username;
        String cardNumber;
        User user;
        Card card;
        if (parameter.containsKey("username")) {
            user = userRepository.findByUsername(parameter.get("username"));
        } else {
            return new ResponseEntity<>("Missing username parameter", HttpStatus.BAD_REQUEST);
        }

        if (parameter.containsKey("cardNumber")) {
            card = cardRepository.findCardByCardNumber(parameter.get("cardNumber"));
        } else {
            return new ResponseEntity<>("Missing card_number parameter", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Create new Payment Successfully", HttpStatus.OK);
    }

}
