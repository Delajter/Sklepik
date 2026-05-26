package com.kacper.myshop.controller;

import com.kacper.myshop.model.Item;
import com.kacper.myshop.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepository;

    public AdminController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("")
    public String adminPage() {
        return "adminview/additem";
    }

    @PostMapping("/add")
    public String addItem(Item item) {
        // Zapisanie obiektu przesłanego z formularza HTML bezpośrednio do bazy danych
        itemRepository.save(item);
        return "redirect:/";
    }
}
