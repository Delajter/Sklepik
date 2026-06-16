package com.kacper.myshop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;

    @Column(name = "imgurl")
    private String imgURL;

    // Konstruktor bez pola ID
    public Item(String name, BigDecimal price, String imgURL) {
        this.name = name;
        this.price = price;
        this.imgURL = imgURL;
    }

    public String getImgUrl() {
        return imgURL;
    }

    public String getImgURL() {
        return imgURL;
    }
}
