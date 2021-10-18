package com.gb.market.services;

import com.gb.market.entities.market.Product;
import com.gb.market.repositories.ProductRepository;
import com.gb.market.utils.ParamsPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ParamsPage paramsPage;
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(Integer page, Integer size,
                                        String title, String min, String max) {

        paramsPage.setPage (page);
        paramsPage.setSize (size);
        paramsPage.setTitle (title);
        paramsPage.setMin (min);
        paramsPage.setMax (max);

        return productRepository.findAll (paramsPage.getSpecification (),
                PageRequest.of (paramsPage.getPage () - 1, paramsPage.getSize ())
        );

    }

    public Product getProductById (Long id) {
        return productRepository.findById (id).get ();
    }

    public ParamsPage getParamsPage () {
        return paramsPage;
    }


}
