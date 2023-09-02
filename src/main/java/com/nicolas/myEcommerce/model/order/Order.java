package com.nicolas.myEcommerce.model.order;

import com.nicolas.myEcommerce.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "order_details")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "payment_id")
    private PaymentDetails paymentDetails;
    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.REMOVE)
    private List<Item> orderItems;
}