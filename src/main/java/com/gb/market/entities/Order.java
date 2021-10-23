package com.gb.market.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    public Long quantity() {
        return orderItems.stream().mapToLong (com.gb.market.entities.OrderItem::getQuantity).sum ();
    }

    public Double totalPrice () {
        return orderItems.stream().mapToDouble (com.gb.market.entities.OrderItem::getTotalPrice).sum ();
    }
}
