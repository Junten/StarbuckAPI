package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Item;
import com.sjsu.project.cmpe202.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(
            value = "/item/get_all_items",
            method = RequestMethod.GET,
            consumes = "application/json")
    public List<Item> getOrdersByUserId() {
        return itemRepository.findAll();
    }
}
