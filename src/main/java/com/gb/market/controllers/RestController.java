package com.gb.market.controllers;

import com.gb.market.entities.market.Product;
import com.gb.market.entities.market.ShoppingCart;
import com.gb.market.services.ProductService;
import com.gb.market.services.СartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private СartService сartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/getCart")
    public ShoppingCart getCart() {
        return сartService.getCart ();
    }

    @PostMapping("/getCart")
    public Product getProduct(@RequestBody ShoppingCart.Item item){
        System.out.println ("@PostMapping(\"/getProduct\") @RequestBody = " + item.toString ());
        return productService.getProductById (item.getId ());
    }

//    @RequestMapping(value = { "/getCart" }, method = RequestMethod.POST)
//    public Product savePerson(@ModelAttribute("item") ShoppingCart.Item item) {
//        System.out.println ("@PostMapping(\"/getProduct\") @RequestBody = " + item.toString ());
//        return productService.getProductById (item.getId ());
//    }

}
