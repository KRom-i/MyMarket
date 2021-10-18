package com.gb.market.services;

import com.gb.market.entities.market.Product;
import com.gb.market.entities.market.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static java.util.Objects.isNull;


@Service
public class СartService {

    @Autowired
    private ProductService productService;

    public void add (Long id, HttpSession session) {
        Product product = productService.getProductById (id);
        if (isNull (product)) return;
        getCart(session).add(product);
    }

    public void remove (Long id, HttpSession session) {
        Product product = productService.getProductById (id);
        if (isNull (product)) return;
        getCart(session).remove(product);
    }

    public void removeItem (Long id, HttpSession session) {
        getCart(session).removeItem(id);
    }

    public ShoppingCart getCart(HttpSession session){

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }


    public void clear (HttpSession session) {
        getCart(session).clear ();
    }


}
