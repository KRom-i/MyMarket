package com.gb.market.controllers;

import com.gb.market.services.СartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class СartController {

    @Autowired
    private СartService сartService;

    @GetMapping("")
    public String shopPage(Model model) {
        model.addAttribute ("shoppingCart", сartService.getCart ());
        return "cart";
    }

    @GetMapping("/add/{id}/{cart}")
    public String addProduct(@PathVariable("id") Long id, @PathVariable("cart") Boolean cart) {
        сartService.add(id);
        if (cart) return "redirect:/cart";
        return "redirect:/shop";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable("id") Long id) {
        сartService.remove (id);
        return "redirect:/cart";
    }

    @GetMapping("/removeItem/{id}")
    public String removeItem(@PathVariable("id") Long id) {
        сartService.removeItem(id);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String removeProduct() {
        сartService.clear();
        return "redirect:/cart";
    }





}
