package com.kacper.myshop.controller;

import com.kacper.myshop.Cart;
import com.kacper.myshop.ItemOperation;
import com.kacper.myshop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    private final CartService cartService;

    public HomeController(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("cart")
    public Cart getCart() {
        return cartService.getCart();
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("items", cartService.getAllItems());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId) {
        cartService.executeOperation(itemId, ItemOperation.INCREASE);
        return "redirect:/";
    }
}
