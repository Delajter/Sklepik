package com.kacper.myshop;

import com.kacper.myshop.model.Item;
import com.kacper.myshop.repository.ItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private final ItemRepository itemRepository;

    // Wstrzyknięcie zależności przez konstruktor
    public DbInit(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (itemRepository.count() == 0) {
            // Zapis domyślnych przedmiotów do bazy danych H2
            itemRepository.saveAll(List.of(
                new Item("Cini Minis", new BigDecimal("12.0"), "https://res.cloudinary.com/dj484tw6k/image/upload/v1592872334/3581.png"),
                new Item("chockapic", new BigDecimal("12.33"), "https://moje-zakupy.pl/214-large_default/platki-nestle-chocapic.jpg"),
                new Item("Płatki marki płatki", new BigDecimal("2.99"), "https://zakupy.auchan.pl/images-v3/91f3b8f0-9eaa-434b-8554-b0f1db433c99/2c4c8c27-51f5-48a3-93b0-c37b1a212d8a/500x500.jpg"),
                new Item("Cini Minis 2", new BigDecimal("12.0"), "https://res.cloudinary.com/dj484tw6k/image/upload/v1592872334/3581.png"),
                new Item("chockapic 2", new BigDecimal("12.33"), "https://moje-zakupy.pl/214-large_default/platki-nestle-chocapic.jpg"),
                new Item("Płatki marki płatki 2", new BigDecimal("2.99"), "https://zakupy.auchan.pl/images-v3/91f3b8f0-9eaa-434b-8554-b0f1db433c99/2c4c8c27-51f5-48a3-93b0-c37b1a212d8a/500x500.jpg")
            ));
        }
    }
}
