package com.kacper.myshop.service;

import com.kacper.myshop.Cart;
import com.kacper.myshop.model.Item;
import com.kacper.myshop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public void addItemToCart(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            cart.addItem(item.get());
        }
    }

    public void decreaseItem(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            cart.decreaseItem(item.get());
        }
    }

    public void removeAllItemsFromCart(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            cart.removeAllItems(item.get());
        }
    }

    public Cart getCart() {
        return cart;
    }
}
