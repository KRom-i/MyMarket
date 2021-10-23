package com.gb.market.controllers;

import com.gb.market.entities.ShoppingCart;
import com.gb.market.services.СartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class СartController {

    @Autowired
    private СartService сartService;

    @GetMapping("")
    public String shopPage(Model model, HttpSession session) {
        demoSession(session);
        model.addAttribute ("shoppingCart", сartService.getCart (session));
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addProduct(@PathVariable("id") Long id, HttpSession session) {
        demoSession(session);
        сartService.add(id, session);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id, HttpSession session) {
        demoSession(session);
        сartService.remove (id, session);
        return "redirect:/cart";
    }

    @GetMapping("/removeItem/{id}")
    public String removeItem(@PathVariable("id") Long id, HttpSession session) {
        demoSession(session);
        сartService.removeItem(id, session);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String removeProduct(HttpSession session) {
        demoSession(session);
        сartService.clear(session);
        return "redirect:/cart";
    }

    @GetMapping("/get")
    @ResponseBody
    public ShoppingCart getCart(HttpSession httpSession){
        return сartService.getCart (httpSession);
    }

    public void demoSession(HttpSession session){
        System.out.println ("session.getId () =" + session.getId ());
    }

}
