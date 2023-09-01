package com.nicolas.myEcommerce.model.product;

import com.nicolas.myEcommerce.model.order.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private boolean isAvailable;
    private Integer quantity;
    private Date createdAt;
    private Date updatedAt;
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Item orderItems;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    private Category category;
}