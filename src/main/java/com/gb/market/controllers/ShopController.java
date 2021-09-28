package com.gb.market.controllers;


import com.gb.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String shopPage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "shop";
    }

}
