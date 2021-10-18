package com.gb.market.controllers;


import com.gb.market.services.ProductService;
import com.gb.market.services.СartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;
    @Autowired
    private СartService сartService;

    @GetMapping("")
    public String getPage(Model model,
                          @RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "size", required = false) Integer size,
                          @RequestParam(value = "title", required = false) String title,
                          @RequestParam(value = "min", required = false) String min,
                          @RequestParam(value = "max", required = false) String max) {

        model.addAttribute("products", productService.getAllProducts(page, size, title, min, max));
        model.addAttribute("paramsPage", productService.getParamsPage ());
        return "shop";
    }

    @GetMapping("/addCartItem/{id}")
    public String addProductToCart(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        сartService.add (id, httpServletRequest.getSession ());
        String referrer = httpServletRequest.getHeader("referer");
        return "redirect:" + referrer;
    }


}
