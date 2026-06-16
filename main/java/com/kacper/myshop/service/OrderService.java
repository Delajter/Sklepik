package com.kacper.myshop.service;

import com.kacper.myshop.dto.OrderDto;
import com.kacper.myshop.mapper.OrderMapper;
import com.kacper.myshop.Cart;
import com.kacper.myshop.model.order.Order;
import com.kacper.myshop.repository.order.OrderItemRepository;
import com.kacper.myshop.repository.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrder(OrderDto orderDto) {
        // 1. Mapowanie i zapis głównego nagłówka zamówienia (generuje OrderId)
        Order order = OrderMapper.mapToOrder(orderDto);
        orderRepository.save(order);

        // 2. Mapowanie pozycji koszyka z uwzględnieniem wygenerowanego ID zamówienia i ich zapis
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));

        // 3. Czyszczenie koszyka klienta po udanej rejestracji zamówienia
        cart.clearCart();
    }
}
