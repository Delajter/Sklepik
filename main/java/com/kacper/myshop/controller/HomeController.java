package com.kacper.myshop.controller;

import com.kacper.myshop.model.Cart;
import com.kacper.myshop.repository.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ItemRepository itemRepository;

    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("items", itemRepository.findAll());

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cartTotalQty", cart.getTotalQuantity());
        model.addAttribute("cartTotalPrice", cart.getTotalPrice());

        return "home";
    }
}
