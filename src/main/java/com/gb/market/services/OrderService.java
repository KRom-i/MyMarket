package com.gb.market.services;

import com.gb.market.entities.Order;
import com.gb.market.entities.OrderItem;
import com.gb.market.entities.ShoppingCart;
import com.gb.market.entities.User;
import com.gb.market.repositories.OrderItemRepository;
import com.gb.market.repositories.OrderRepository;
import com.gb.market.repositories.OrderStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    OrderStatusRepo orderStatusRepo;


    public Order save (ShoppingCart cart, HttpSession session) {

        User user = (User) session.getAttribute ("user");

        Order order = new Order ();
        order.setOrderItems (new ArrayList<OrderItem> ());
        order.setUser (user);
        order.setCreateAt (LocalDateTime.now ());
        order.setStatus (orderStatusRepo.findById (1L).get ());

        orderRepository.save (order);

        for (OrderItem orderItem: cart.getOrderItems ()) {
            orderItem.setOrder (order);
            order.getOrderItems ().add (orderItemRepository.save (orderItem));
        }

        cart.clear ();

        return order;
    }


    public void getHistory () {
    }


}
