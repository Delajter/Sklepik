package com.kacper.myshop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() {
        return items;
    }

    public void addItem(Item item) {
        for (CartItem cartItem : items) {
            if (cartItem.getItem().getName().equals(item.getName())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(item, 1));
    }



    public void increaseQuantity(String itemName) {
        for (CartItem cartItem : items) {
            if (cartItem.getItem().getName().equals(itemName)) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
    }

    public void decreaseQuantity(String itemName) {
        for (CartItem cartItem : items) {
            if (cartItem.getItem().getName().equals(itemName)) {
                int newQty = cartItem.getQuantity() - 1;
                if (newQty <= 0) {
                    items.remove(cartItem);
                } else {
                    cartItem.setQuantity(newQty);
                }
                return;
            }
        }
    }

    public void clearCart() {
        items.clear();
    }



    public void removeItem(String itemName) {
        items.removeIf(cartItem -> cartItem.getItem().getName().equals(itemName));
    }

    public int getTotalQuantity() {
        return items.stream().mapToInt(CartItem::getQuantity).sum();
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(CartItem::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        items.clear();
    }
}
