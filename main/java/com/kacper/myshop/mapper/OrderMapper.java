package com.kacper.myshop.mapper;

import com.kacper.myshop.dto.OrderDto;
import com.kacper.myshop.Cart;
import com.kacper.myshop.model.order.Order;
import com.kacper.myshop.model.order.OrderItem;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    // Mapowanie podstawowych danych klienta z formularza DTO na encję Order
    public static Order mapToOrder(OrderDto orderDto) {
        return Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .address(orderDto.getAddress())
                .postCode(orderDto.getPostCode())
                .city(orderDto.getCity())
                .created(LocalDateTime.now())
                .build();
    }

    // Mapowanie zawartości koszyka na listę encji OrderItem przypisanych do danego zamówienia
    public static List<OrderItem> mapToOrderItemList(Cart cart, Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (var ci : cart.getCartItems()) {
            orderItems.add(new OrderItem(order, ci.getItem().getId(), ci.getCounter()));
        }
        return orderItems;
    }
}
