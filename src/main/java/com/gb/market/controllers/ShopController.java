package com.gb.market.controllers;


import com.gb.market.entites.Product;
import com.gb.market.repositories.specifications.ProductSpecs;
import com.gb.market.services.ProductService;
import com.gb.market.services.ShoppingCartService;
import com.gb.market.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/shop")
public class ShopController {
    private static final int INITIAL_PAGE = 0;
    private static final int PAGE_SIZE = 5;

    private UserService userService;
    private ProductService productService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String shopPage(Model model,
                           @RequestParam(value = "page") Optional<Integer> page,
                           @RequestParam(value = "word", required = false) String word,
                           @RequestParam(value = "min", required = false) Double min,
                           @RequestParam(value = "max", required = false) Double max
    ) {

        final int currentPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Specification<Product> spec = Specification.where(null);
        StringBuilder filters = new StringBuilder();
        if (word != null) {
            spec = spec.and(ProductSpecs.titleContains(word));
            filters.append("&word=" + word);
        }
        if (min != null) {
            spec = spec.and(ProductSpecs.priceGreaterThanOrEq(min));
            filters.append("&min=" + min);
        }
        if (max != null) {
            spec = spec.and(ProductSpecs.priceLesserThanOrEq(max));
            filters.append("&max=" + max);
        }

        Page<Product> products = productService.getProductsWithPagingAndFiltering(currentPage, PAGE_SIZE, spec);

        model.addAttribute("products", products.getContent());
        model.addAttribute("page", currentPage);
        model.addAttribute("totalPage", products.getTotalPages());

        model.addAttribute("filters", filters.toString());

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("word", word);

        return "shop-page";
    }

    private final static String QUEUE_NAME = "hello";

//    @GetMapping("/cart/add/{id}")
//    public String addProductToCart(Model model, @PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
//        shoppingCartService.addToCart(httpServletRequest.getSession(), id);
//        String referrer = httpServletRequest.getHeader("referer");
//
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        try (Connection connection = factory.newConnection();
//             Channel channel = connection.createChannel()){
//            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//            String msg = "Hello World!";
//            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
//            System.out.println("sent " + msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "redirect:" + referrer;
//    }
}
