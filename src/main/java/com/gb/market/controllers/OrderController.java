package com.gb.market.controllers;

import com.gb.market.entities.Order;
import com.gb.market.entities.ShoppingCart;
import com.gb.market.entities.User;
import com.gb.market.services.OrderService;
import com.gb.market.services.СartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private СartService сartService;
    @Autowired
    private OrderService orderService;

    @GetMapping("")
    public String shopPage(Model model, HttpSession session) {
        model.addAttribute ("shoppingCart", сartService.getCart (session));
        return "order_save";
    }


    @PostMapping("/save")
    public String addProduct(Model model, HttpSession session) {
        ShoppingCart cart = сartService.getCart (session);
        Order order = orderService.save(cart, session);
        model.addAttribute ("order", order);
        return "order_status";
    }

    @GetMapping("/history")
    public String history (Model model){
        orderService.getHistory();
        return "index";
    }
}
