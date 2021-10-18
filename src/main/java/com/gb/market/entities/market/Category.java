package com.gb.market.entities.market;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;
}
