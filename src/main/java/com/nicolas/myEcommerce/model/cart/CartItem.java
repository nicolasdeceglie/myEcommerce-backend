package com.nicolas.myEcommerce.model.cart;

import com.nicolas.myEcommerce.model.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "cart_item")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Date createdAt;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "product_id")
    private Product product;
}