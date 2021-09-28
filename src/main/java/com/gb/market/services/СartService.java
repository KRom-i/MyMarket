package com.gb.market.services;

import com.gb.market.entities.market.Product;
import com.gb.market.entities.market.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;


@Service
public class СartService {

    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCart cart;

    public void add (Long id) {
        Product product = productService.getProductById (id);
        if (isNull (product)) return;
        cart.add(product);
    }

    public void remove (Long id) {
        Product product = productService.getProductById (id);
        if (isNull (product)) return;
        cart.remove(product);
    }

    public void removeItem (Long id) {
        cart.removeItem(id);
    }

    public ShoppingCart getCart(){
        return cart;
    }

    public void clear () {
        cart.clear ();
    }


}
