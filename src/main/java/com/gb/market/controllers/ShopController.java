package com.gb.market.controllers;


import com.gb.market.entities.market.Product;
import com.gb.market.services.ProductService;
import com.gb.market.services.СartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;
    @Autowired
    private СartService сartService;

    @GetMapping("")
    public String getShop(Model model,
                          @RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "size", required = false) Integer size,
                          @RequestParam(value = "title", required = false) String title,
                          @RequestParam(value = "min", required = false) String min,
                          @RequestParam(value = "max", required = false) String max) {

        model.addAttribute("products", productService.getAllProducts(page, size, title, min, max));
        model.addAttribute("paramsPage", productService.getParamsPage ());
        model.addAttribute ("page", productService.getAllProducts(page, size, title, min, max));
        return "shop";
    }

    @GetMapping(value = "/page")
    @ResponseBody
    public Page<Product> getPage(@RequestParam(value = "page", required = false) Integer page,
                                 @RequestParam(value = "size", required = false) Integer size,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "min", required = false) String min,
                                 @RequestParam(value = "max", required = false) String max){
       return productService.getAllProducts(page, size, title, min, max);
    }

    @GetMapping("/get-user")
    @ResponseBody
    public Object getUser(HttpSession session){
        return new HashMap<String, Object> (){{
            put ("id", session.getId ());
            put ("cart", сartService.getCart (session));
        }};
    }


}
