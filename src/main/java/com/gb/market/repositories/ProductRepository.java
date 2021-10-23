package com.gb.market.repositories;

import com.gb.market.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    Product findOneByTitle(String title);

    Product findTopByOrderByIdDesc();

    Page<Product> findByOrderByIdDesc (Pageable pageable);

}