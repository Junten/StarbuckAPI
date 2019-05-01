package com.sjsu.project.cmpe202.controller;

import com.sjsu.project.cmpe202.model.Item;
import com.sjsu.project.cmpe202.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/item/get_all_items",
            method = RequestMethod.GET,
            consumes = "application/json")
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/item/add_new_item",
            method = RequestMethod.POST,
            consumes = "application/json")
    public HttpStatus addNewItem(Map<String, String> item) {
        Item newItem = new Item();
        newItem.setImageUrl(item.get("image_url"));
        Double price = Double.parseDouble(item.get("price"));
        newItem.setPrice(price);
        newItem.setProduct(item.get("product"));
        itemRepository.save(newItem);
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @RequestMapping(
            value = "/item/delete_item_by_item_id",
            method = RequestMethod.DELETE,
            consumes = "application/json")
    public HttpStatus deleteItemByItemId(Map<String, Integer> item) {
        itemRepository.deleteById(item.get("item_id"));
        return HttpStatus.OK;
    }
}
