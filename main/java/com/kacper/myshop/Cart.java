package com.kacper.myshop;

import com.kacper.myshop.model.Item;
import com.kacper.myshop.model.CartItem;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lombok.Getter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        Predicate<CartItem> matchesItem = ci -> ci.getItem().getId().equals(item.getId());
        Consumer<CartItem> increaseCounter = CartItem::increaseCounter;

        cartItems.stream()
                .filter(matchesItem)
                .findFirst()
                .ifPresentOrElse(
                        increaseCounter,
                        () -> cartItems.add(new CartItem(item)));
        recalculatePriceAndCounter();
    }

    public void removeItem(Item item) {
        Predicate<CartItem> matchesItem = ci -> ci.getItem().getId().equals(item.getId());
        Consumer<CartItem> decreaseAndMaybeRemove = ci -> {
            ci.decreaseCounter();
            if (ci.hasZeroItems()) {
                cartItems.remove(ci);
            }
        };

        cartItems.stream()
                .filter(matchesItem)
                .findFirst()
                .ifPresent(decreaseAndMaybeRemove);
        recalculatePriceAndCounter();
    }

    public void removeItemCompletely(Item item) {
        Predicate<CartItem> matchesItem = ci -> ci.getItem().getId().equals(item.getId());
        cartItems.removeIf(matchesItem);
        recalculatePriceAndCounter();
    }

    public void clear() {
        cartItems.clear();
        recalculatePriceAndCounter();
    }

    private void recalculatePriceAndCounter() {
        this.counter = 0;
        this.sum = BigDecimal.ZERO;

        Consumer<CartItem> accumulator = ci -> {
            this.counter += ci.getCounter();
            this.sum = this.sum.add(ci.getPrice());
        };

        cartItems.forEach(accumulator);
    }
}
