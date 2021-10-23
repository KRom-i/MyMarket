package com.gb.market.repositories;

import com.gb.market.entities.OrderStatus;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepo extends CrudRepository<OrderStatus, Long> {

}
