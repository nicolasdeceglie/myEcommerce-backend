package com.nicolas.myEcommerce.model.product;

import com.nicolas.myEcommerce.model.order.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

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
    private boolean isAvailable;
    private Integer quantity;
    private String createdAt;
    private String updatedAt;
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Image> images;
    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Item orderItems;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "discount_id")
    private Discount discount;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    private Category category;
}