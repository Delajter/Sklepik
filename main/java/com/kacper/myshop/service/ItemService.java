package com.kacper.myshop.service;

import com.kacper.myshop.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//!war konn , war repo(inject servis -logic ),service

//*baza h2-roz



@Controller
public class ItemService {

    private final List<Item> items = new CopyOnWriteArrayList<>();

    public ItemService() {
        items.add(new Item("Cini Minis", new BigDecimal("12.0"),
                "https://res.cloudinary.com/dj484tw6k/image/upload/v1592872334/3581.png"));
        items.add(new Item("chockapic", new BigDecimal("12.33"),
                "https://moje-zakupy.pl/214-large_default/platki-nestle-chocapic.jpg"));
        items.add(new Item("Płatki marki płatki", new BigDecimal("2.99"),
                "https://zakupy.auchan.pl/images-v3/91f3b8f0-9eaa-434b-8554-b0f1db433c99/2c4c8c27-51f5-48a3-93b0-c37b1a212d8a/500x500.jpg"));


        items.add(new Item("Cini Minis 2", new BigDecimal("12.0"),
                "https://res.cloudinary.com/dj484tw6k/image/upload/v1592872334/3581.png"));
        items.add(new Item("chockapic 2", new BigDecimal("12.33"),
                "https://moje-zakupy.pl/214-large_default/platki-nestle-chocapic.jpg"));
        items.add(new Item("Płatki marki płatki 2", new BigDecimal("2.99"),
                "https://zakupy.auchan.pl/images-v3/91f3b8f0-9eaa-434b-8554-b0f1db433c99/2c4c8c27-51f5-48a3-93b0-c37b1a212d8a/500x500.jpg"));

    }

    public List<Item> getAllItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItemByName(String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
