package com.kacper.myshop.controller;

import com.kacper.myshop.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final Cart cart;

    public OrderController(Cart cart) {
        this.cart = cart;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("cart", cart);
        return "cartView";
    }
}
