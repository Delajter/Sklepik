package com.kacper.myshop.controller;

import com.kacper.myshop.Cart;
import com.kacper.myshop.model.Item;
import com.kacper.myshop.repository.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final Cart cart;

    public HomeController(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("cart", cart);
        return "home";
    }

    @GetMapping("/addItemToCart/{id}")
    public String addItemToCart(@PathVariable Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe ID produktu: " + id));
        cart.addItem(item);
        return "redirect:/";
    }
}
