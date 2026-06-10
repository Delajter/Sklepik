package com.kacper.myshop;

import com.kacper.myshop.model.Item;
import com.kacper.myshop.model.CartItem;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import lombok.Getter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    private Optional<CartItem> getCartItemByItem(Item item) {
        return cartItems.stream()
                .filter(ci -> ci.isEquals(item))
                .findFirst();
    }

    public void addItem(Item item) {
        getCartItemByItem(item).ifPresentOrElse(
                CartItem::increaseCounter,
                () -> cartItems.add(new CartItem(item)));
        recalculatePriceAndCounter();
    }

    public void removeItem(Item item) {
        Optional<CartItem> oCartItem = getCartItemByItem(item);
        if (oCartItem.isPresent()) {
            CartItem ci = oCartItem.get();
            ci.decreaseCounter();
            if (ci.hasZeroItems()) {
                cartItems.remove(ci);
            }
            recalculatePriceAndCounter();
        }
    }

    public void removeItemCompletely(Item item) {
        Predicate<CartItem> matchesItem = ci -> ci.getItem().getId().equals(item.getId());
        cartItems.removeIf(matchesItem);
        recalculatePriceAndCounter();
    }

    public void decreaseItem(Item item) {
        Optional<CartItem> cartItemOpt = getCartItemByItem(item);
        if (cartItemOpt.isPresent()) {
            CartItem cartItem = cartItemOpt.get();
            cartItem.decreaseCounter();
            if (cartItem.hasZeroItems()) {
                removeAllItems(item);
            }
        }
        recalculatePriceAndCounter();
    }

    public void removeAllItems(Item item) {
        cartItems.removeIf(i -> i.isEquals(item));
        recalculatePriceAndCounter();
    }

    public void clear() {
        cartItems.clear();
        recalculatePriceAndCounter();
    }

    private void recalculatePriceAndCounter() {
        this.sum = cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.counter = cartItems.stream()
                .mapToInt(CartItem::getCounter)
                .sum();
    }
}
