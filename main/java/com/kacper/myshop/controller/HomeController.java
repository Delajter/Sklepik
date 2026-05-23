package com.kacper.myshop.controller;

import com.kacper.myshop.service.ItemService;
import com.kacper.myshop.model.Cart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ItemService itemService;

    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private void populateCartAttributes(Model model, HttpSession session) {
        Cart cart = getCart(session);
        model.addAttribute("cartTotalQty", cart.getTotalQuantity());
        model.addAttribute("cartTotalPrice", cart.getTotalPrice());
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("items", itemService.getAllItems());
        populateCartAttributes(model, session);
        return "home";
    }
}
