package com.kacper.myshop.controller;

import com.kacper.myshop.model.Item;
import com.kacper.myshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final ItemService itemService;

    public AdminController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "adminview/addItem";
    }

    @PostMapping("/admin")
    public String addItem(Item item) {
        itemService.addItem(item);
        return "redirect:/";
    }
}
