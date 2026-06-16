package com.kacper.myshop.controller;

import com.kacper.myshop.model.Item;
import com.kacper.myshop.model.order.Order;
import com.kacper.myshop.repository.ItemRepository;
import com.kacper.myshop.repository.order.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public AdminController(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("")
    public String adminPage() {
        return "adminview/additem";
    }

    @PostMapping("/add")
    public String addItem(Item item) {
        itemRepository.save(item);
        return "redirect:/";
    }

    @GetMapping("/showorders")
    @ResponseBody
    public List<Order> showOrders() {
        return orderRepository.findAll();
    }
}
