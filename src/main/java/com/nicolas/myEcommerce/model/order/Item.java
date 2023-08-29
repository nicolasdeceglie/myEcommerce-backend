package com.nicolas.myEcommerce.model.order;

import com.nicolas.myEcommerce.model.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "order_items")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Detail order;
    @OneToOne(mappedBy = "orderItems", fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}