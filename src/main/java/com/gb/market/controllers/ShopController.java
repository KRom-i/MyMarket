package com.gb.market.controllers;


import com.gb.market.entities.market.Product;
import com.gb.market.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String shopPage(Model model) {
        Page<Product> products = productService.getAllProducts(PageRequest.of (0, 10));
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/{page}/{size}")
    public String getPage(Model model,
                          @PathVariable("page") Integer page,
                          @PathVariable("size") Integer size) {
        Page<Product> products = productService.getAllProducts(PageRequest.of (page, size));
        model.addAttribute("products", products);
        return "shop";
    }


}
