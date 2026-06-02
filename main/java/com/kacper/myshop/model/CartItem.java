package com.kacper.myshop.model;

import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class CartItem {
    private Item item;
    private int counter;
    private BigDecimal price;

    public CartItem(Item item) {
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }

    public void increaseCounter() {
        this.counter++;
        recalculate();
    }

    public void decreaseCounter() {
        if (this.counter > 0) {
            this.counter--;
            recalculate();
        }
    }

    public boolean hasZeroItems() {
        return this.counter == 0;
    }

    private void recalculate() {
        if (this.item != null && this.item.getPrice() != null) {
            this.price = this.item.getPrice().multiply(new BigDecimal(this.counter));
        }
    }
}
