package com.sjsu.project.cmpe202.controller;


import com.sjsu.project.cmpe202.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "Hello World!";

    }
}
