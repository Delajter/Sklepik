package com.kacper.myshop.model.order;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "orderitem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderItemId")
    private Long orderItemId;
    
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @Column(name = "itemId")
    private Long itemId;
    private int amount;

    // Konstruktor inicjalizujący pola z pominięciem autoinkrementowanego ID i z referencją do Order
    public OrderItem(Order order, Long itemId, int amount) {
        this.order = order;
        this.itemId = itemId;
        this.amount = amount;
    }
}
