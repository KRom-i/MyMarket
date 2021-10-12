package com.gb.market.services;

import com.gb.market.entities.market.Product;
import com.gb.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;



@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll (pageable);
    }

    public Product getProductById (Long id) {
        return productRepository.findById (id).get ();
    }
}
