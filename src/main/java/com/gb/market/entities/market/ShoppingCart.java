package com.gb.market.entities.market;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import static java.util.Objects.isNull;


@Component
@Data
public class ShoppingCart {


    private List<OrderItem> orderItems;
    private Double totalPrice;
    private Long quantity;

    public List<OrderItem> getOrderItems () {
        if (isNull (orderItems)){
            orderItems = new ArrayList<> ();
        }
        return orderItems;
    }

    public void add (Product product) {

        OrderItem item = getOrderItemFromProduct (product.getId ());

        if (isNull (item)){

            item = new OrderItem ();
            item.setQuantity (1L);
            item.setItemPrice (product.getPrice ());
            item.setTotalPrice (product.getPrice ());
            item.setProduct (product);
            getOrderItems ().add (item);
            calculator();

        } else {

            item.setTotalPrice (item.getTotalPrice () + product.getPrice ());
            item.setQuantity (item.getQuantity () + 1L);
            calculator();
        };
    }

    public void remove (Product product) {

        OrderItem item = getOrderItemFromProduct (product.getId ());

        if (isNull (item)){
            return;
        }

        Long quantity = item.getQuantity () - 1L;

        if (quantity <= 0){
            getOrderItems ().remove (item);
            calculator();
            return;
        }

        item.setTotalPrice (item.getTotalPrice () - product.getPrice ());
        item.setQuantity (quantity);
        calculator();
    }

    public void removeItem (Long id) {
        OrderItem item = getOrderItemFromProduct (id);
        if (isNull (item)){
           return;
        }
        getOrderItems ().remove (item);
        calculator();
    }

    private void calculator(){
        calcQuantity();
        calcTotalPrice ();
    }

    private void calcQuantity() {
        this.quantity = getOrderItems ().stream().mapToLong (OrderItem::getQuantity).sum ();
    }

    private void calcTotalPrice () {
        this.totalPrice = getOrderItems ().stream().mapToDouble (OrderItem::getTotalPrice).sum ();
    }

    public void clear () {
        getOrderItems ().clear ();
    }

    public boolean isEmpty(){
        return getOrderItems ().isEmpty ();
    }

    private OrderItem getOrderItemFromProduct(Long id) {
        return getOrderItems ().stream().filter(o ->
                o.getProduct().getId().equals(id)).findFirst().orElse(null);
    }

}
