package com.kacper.myshop.controller;

import com.kacper.myshop.Cart;
import com.kacper.myshop.model.Item;
import com.kacper.myshop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final ItemService itemService;
    private final Cart cart;

    public CartController(ItemService itemService, Cart cart) {
        this.itemService = itemService;
        this.cart = cart;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cart", cart);
        return "cartView";
    }

    @GetMapping("/cart/add")
    public String addToCart(@RequestParam("name") String name) {
        Item item = itemService.getItemByName(name);
        if (item != null) {
            cart.addItem(item);
        }
        return "redirect:/";
    }

    @GetMapping("/cart/increase")
    public String increaseQuantity(@RequestParam("name") String name) {
        Item item = itemService.getItemByName(name);
        if (item != null) {
            cart.addItem(item);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/decrease")
    public String decreaseQuantity(@RequestParam("name") String name) {
        Item item = itemService.getItemByName(name);
        if (item != null) {
            cart.removeItem(item);
        }
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam("name") String name) {
        Item item = itemService.getItemByName(name);
        if (item != null) {
            cart.removeItemCompletely(item);
        }
        return "redirect:/cart";
    }

    @GetMapping("/summary")
    public String viewSummary(Model model) {
        model.addAttribute("cart", cart);
        return "summary";
    }

    @PostMapping("/order")
    public String placeOrder(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("address") String address,
            @RequestParam("postCode") String postCode,
            @RequestParam("city") String city,
            Model model) {
        
        cart.clear();
        
        model.addAttribute("infoMsg", "Dziękujemy " + firstName + " " + lastName + "- Zamówienie zostało pomyślnie złożone może je wyślemy.");
        model.addAttribute("cart", cart);
        
        return "summary";
    }
}
