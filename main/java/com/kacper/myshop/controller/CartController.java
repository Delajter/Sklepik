package com.kacper.myshop.controller;

import com.kacper.myshop.model.Cart;
import com.kacper.myshop.model.Item;
import com.kacper.myshop.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final ItemService itemService;

    public CartController(ItemService itemService) {
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

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        Cart cart = getCart(session);
        model.addAttribute("cart", cart);
        populateCartAttributes(model, session);
        return "cartView";
    }

    @GetMapping("/cart/add")
    public String addToCart(@RequestParam("name") String name, HttpSession session) {
        Item item = itemService.getItemByName(name);
        if (item != null) {
            Cart cart = getCart(session);
            cart.addItem(item);
        }
        return "redirect:/";
    }

    @GetMapping("/cart/increase")
    public String increaseQuantity(@RequestParam("name") String name, HttpSession session) {
        Cart cart = getCart(session);
        cart.increaseQuantity(name);
        return "redirect:/cart";
    }

    @GetMapping("/cart/decrease")
    public String decreaseQuantity(@RequestParam("name") String name, HttpSession session) {
        Cart cart = getCart(session);
        cart.decreaseQuantity(name);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove")
    public String removeFromCart(@RequestParam("name") String name, HttpSession session) {
        Cart cart = getCart(session);
        cart.removeItem(name);
        return "redirect:/cart";
    }

    @GetMapping("/summary")
    public String viewSummary(Model model, HttpSession session) {
        Cart cart = getCart(session);
        model.addAttribute("cart", cart);
        populateCartAttributes(model, session);
        return "summary";
    }

    @PostMapping("/order")
    public String placeOrder(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("address") String address,
            @RequestParam("postCode") String postCode,
            @RequestParam("city") String city,
            Model model,
            HttpSession session) {
        
        Cart cart = getCart(session);
        

        cart.clear();
        
        model.addAttribute("infoMsg", "Dziękujemy " + firstName + " " + lastName + "- Zamówienie zostało pomyślnie złożone może je wyślemy.");
        
        populateCartAttributes(model, session);
        model.addAttribute("cart", cart);
        
        return "summary";
    }
}
