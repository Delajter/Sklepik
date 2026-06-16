package com.kacper.myshop.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "`order`") // Zmiana nazwy tabeli z powodu konfliktu ze słowem kluczowym SQL
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    private String address;

    @Column(name = "postCode")
    private String postCode;

    private String city;
    private LocalDateTime created;

    // Relacja OneToMany wiążąca zamówienie z jego pozycjami szczegółowymi
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItems;
}
