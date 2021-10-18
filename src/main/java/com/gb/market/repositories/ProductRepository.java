package com.gb.market.repositories;

import com.gb.market.entities.market.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

}
