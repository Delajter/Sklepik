package com.kacper.myshop.service;

import com.kacper.myshop.Cart;
import com.kacper.myshop.ItemOperation;
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

    public void executeOperation(long itemId, ItemOperation operation) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        
        if (oItem.isPresent()) {
            Item item = oItem.get();
            
            switch (operation) {
                case INCREASE:
                    cart.addItem(item);
                    break;
                case DECREASE:
                    cart.decreaseItem(item);
                    break;
                case REMOVE:
                    cart.removeAllItems(item);
                    break;
            }
        }
    }

    public Cart getCart() {
        return cart;
    }
}
