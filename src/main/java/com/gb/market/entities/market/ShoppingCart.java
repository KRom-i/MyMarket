package com.gb.market.entities.market;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.LinkedHashMap;

import static java.util.Objects.isNull;


@Component
@Data
public class ShoppingCart {

    @Data
    @ToString
    public class Item{

        private Long id;
        private Product product;
        private Integer num;
        private Double sum;
        private String category, title;

        public Item (Product product) {
            this.id = product.getId ();
            this.num = 1;
            this.sum = product.getPrice ();
            this.category = product.getCategory ().getTitle ();
            this.title = product.getTitle ();
        }

        public Integer getNum () {
            return num;
        }

        public Double getSum () {
            return sum;
        }


    }
    private int numItems;
    private double sumItems;

    private LinkedHashMap<Long, Item> items;

    private LinkedHashMap<Long, Item>  getMap () {
        if (isNull(items)) {
            items = new LinkedHashMap<> ();
        }
        return items;
    }

    public Collection<Item> getItems () {
        return getMap ().values ();
    }

    public void add (Product product) {

        if (checkById(product.getId ())){

            Item item = getMap ().get (product.getId ());
            item.sum += product.getPrice ();
            item.num += 1;

        } else {
            getMap ().put (product.getId (), new Item (product));
        };

        numItems = num ();
        sumItems = sum ();
    }

    public void remove (Product product) {

        if (!checkById(product.getId ())) return;

        Item item = getMap ().get (product.getId ());

        if (item.num <= 1){
            getMap ().remove (product.getId ());
            
        } else {
            item.sum -= product.getPrice ();
            item.num -= 1;
        }

        numItems = num ();
        sumItems = sum ();
    }

    public void removeItem (Long id) {
        getMap ().remove (id);
        numItems = num ();
        sumItems = sum ();
    }

    public int num() {
        return getItems ().stream().mapToInt(Item::getNum).sum ();
    }

    public double sum () {
        return getItems ().stream().mapToDouble (Item::getSum).sum ();
    }

    public void clear () {
        getMap ().clear ();
    }

    public boolean isEmpty(){
        return getMap ().isEmpty ();
    }

    public boolean checkById(Long id){
        return getMap ().containsKey (id);
    }

}
